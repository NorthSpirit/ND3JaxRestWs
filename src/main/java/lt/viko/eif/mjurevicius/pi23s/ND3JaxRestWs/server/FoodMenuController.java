package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model.FoodItem;
import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model.FoodMenu;
import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model.FoodMenus;
import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.services.JSONMarshall;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Controller for managing food menus.
 * Provides endpoints to retrieve all menus, a specific menu by name, and the image associated with a menu.
 */
@RestController
@RequestMapping("/foodmenus")
@Tag(name = "Food Menu Controller", description = "Endpoints for managing food menus")
public class FoodMenuController {

    /**
     * Normalizes a given name to a lowercase string with all whitespace removed.
     * This is used for case-insensitive and whitespace-insensitive matching.
     *
     * @param name The original name string.
     * @return A normalized version of the name.
     */
    private String normalizeName(String name) {
        return name.toLowerCase().replaceAll("\\s+", "");
    }

    /**
     * Determines the appropriate media type (MIME type) based on the file extension.
     *
     * @param extension The file extension (e.g., "jpg", "png").
     * @return The corresponding MIME type, or {@code application/octet-stream} if the extension is not recognized.
     */
    private String getMimeTypeFromExtension(String extension) {
        switch (extension.toLowerCase()) {
            case "jpg":
            case "jpeg":
                return MediaType.IMAGE_JPEG_VALUE;
            case "png":
                return MediaType.IMAGE_PNG_VALUE;
            case "gif":
                return MediaType.IMAGE_GIF_VALUE;
            default:
                return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
    }

    /**
     * Retrieves all available food menus.
     *
     * @return ResponseEntity containing a {@link FoodMenus} object with a list of all menus,
     * or a 500 Internal Server Error if there's an issue unmarshalling the data.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all food menus")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of all food menus", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = FoodMenus.class)))
    public ResponseEntity<FoodMenus> getAllMenus() {
        FoodMenus foodMenus = JSONMarshall.unmashallFoodMenu("FoodMenus");

        if (foodMenus == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        UriComponentsBuilder baseUriBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        //SELF LINK
        URI selfUri = baseUriBuilder.build().toUri();
        foodMenus.addLink(new Link("self", selfUri.toString()));

        // LINKS TO INDIVIDUAL MENUS AND THEIR FOOD ITEMS
        if (foodMenus.getFoodMenuList() != null) {
            for (FoodMenu menu : foodMenus.getFoodMenuList()) {
                //MENU LINK
                URI menuUri = baseUriBuilder.build().toUri();
                menu.addLink(new Link("menu", menuUri.toString()));

                // FOOD ITEMS LINKS
                if (menu.getMenuItems() != null) {
                    for (FoodItem item : menu.getMenuItems()) {
                        URI itemUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/foodmenus/{menuName}/{itemName}")
                                .buildAndExpand(normalizeName(menu.getMenuName()), normalizeName(item.getName()))
                                .toUri();
                        item.addLink(new Link("item", itemUri.toString()));
                    }
                }
            }
        }
        return ResponseEntity.ok(foodMenus);
    }

    /**
     * Retrieves a specific food menu by its name.
     *
     * @param name The name of the menu to retrieve (case and whitespace insensitive).
     * @return ResponseEntity containing the {@link FoodMenu} if found, or a 404 Not Found status if not.
     */
    @GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a food menu by its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Food menu found", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = FoodMenu.class))),
            @ApiResponse(responseCode = "404", description = "Food menu not found")
    })
    public ResponseEntity<FoodMenu> getMenuByName(@PathVariable String name) {
        FoodMenus foodMenus = JSONMarshall.unmashallFoodMenu("FoodMenus");
        if (foodMenus == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<FoodMenu> menuList = foodMenus.getFoodMenuList();
        if (menuList != null) {
            for (FoodMenu menu : menuList) {
                if (normalizeName(menu.getMenuName()).equalsIgnoreCase(name)) {
                    String basePath = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/..").build().toUriString();

                    UriComponentsBuilder UriBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri();
                    //SELF LINK
                    URI selfUri = UriBuilder.build().toUri();
                    foodMenus.addLink(new Link("self", selfUri.toString()));

                    //PICTURE LINK
                    URI imageUri = URI.create(basePath + "/" + name + "/image");
                    menu.addLink(new Link("image", imageUri.toString()));

                    //MENUS LINK
                    URI menusUri = UriBuilder.path("/../..").path("/foodmenus").build().toUri();
                    menu.addLink(new Link("menus", menusUri.toString()));

                    //ITEMS LINKS
                    if (menu.getMenuItems() != null) {
                        for (FoodItem item : menu.getMenuItems()) {
                            URI itemUri = URI.create(basePath + "/" + name + "/" + normalizeName(item.getName()));
                            item.addLink(new Link("item", itemUri.toString()));
                        }
                    }
                    return ResponseEntity.ok(menu);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Retrieves the image associated with a specific food menu.
     *
     * @param name The name of the menu whose image is to be retrieved (case and whitespace insensitive).
     * @return ResponseEntity containing the image data as a byte array with the appropriate Content-Type,
     * or a 404 Not Found status if the menu or image is not found, or an IOException occurs.
     * @throws IOException If an error occurs while reading the image file.
     */
    @GetMapping(path = "/{name}/image", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, "image/*"})
    @Operation(summary = "Get the image for a specific food menu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image retrieved successfully", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "image/*", schema = @io.swagger.v3.oas.annotations.media.Schema(type = "string", format = "binary"))),
            @ApiResponse(responseCode = "404", description = "Menu or image not found")
    })
    public ResponseEntity<byte[]> getMenuImage(@PathVariable String name) throws IOException {
        FoodMenus foodMenus = JSONMarshall.unmashallFoodMenu("FoodMenus");

        if (foodMenus == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (FoodMenu menu : foodMenus.getFoodMenuList()) {
            if (normalizeName(menu.getMenuName()).equalsIgnoreCase(name)) {
                String baseName = menu.getMenuPicName();
                String baseNameWithoutExt = baseName.contains(".")
                        ? baseName.substring(0, baseName.lastIndexOf('.'))
                        : baseName;

                String[] extensions = {".jpg", ".jpeg", ".png", ".img"};
                for (String ext : extensions) {
                    String resourcePath = "images/" + baseNameWithoutExt + ext;
                    java.io.InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath);
                    if (is != null) {
                        String fileExtension = ext.substring(1);
                        String mimeType = getMimeTypeFromExtension(fileExtension);
                        byte[] imageData = is.readAllBytes();
                        is.close();
                        return ResponseEntity.ok()
                                .contentType(MediaType.parseMediaType(mimeType))
                                .header("Content-Disposition", "inline; filename=\"" + baseName + ext + "\"")
                                .body(imageData);
                    }
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}