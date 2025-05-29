package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model.*;
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

import java.net.URI;

/**
 * Controller for retrieving specific food items, either from a menu or an order.
 * Provides endpoints to fetch a food item by its name within a given menu or order ID.
 */
@RestController
@RequestMapping("/")
@Tag(name = "Food Item Controller", description = "Endpoints for retrieving specific food items")
public class FoodItemController {

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
     * Retrieves a specific food item from a given menu by its name.
     *
     * @param menuName The name of the menu to search within (case and whitespace insensitive).
     * @param itemName The name of the food item to retrieve (case and whitespace insensitive).
     * @return ResponseEntity containing the {@link FoodItem} if found, or a 404 Not Found status if not.
     */
    @GetMapping(path = "/foodmenus/{menuName}/{itemName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a food item from a specific menu by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Food item found", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = FoodItem.class))),
            @ApiResponse(responseCode = "404", description = "Food item or menu not found")
    })
    public ResponseEntity<FoodItem> getFoodItemFromMenu(
            @PathVariable String menuName,
            @PathVariable String itemName) {
        FoodMenus foodMenus = JSONMarshall.unmashallFoodMenu("FoodMenus");
        if (foodMenus == null || foodMenus.getFoodMenuList() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (FoodMenu menu : foodMenus.getFoodMenuList()) {
            if (normalizeName(menu.getMenuName()).equalsIgnoreCase(menuName) && menu.getMenuItems() != null) {
                for (FoodItem item : menu.getMenuItems()) {
                    if (normalizeName(item.getName()).equalsIgnoreCase(itemName)) {
                        UriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri();
                        // SELF LINK
                        URI selfUri = uriBuilder.build().toUri();
                        item.addLink(new Link("self", selfUri.toString()));
                        // MENU LINK
                        URI menuUri = uriBuilder.path("/../../..").path("/foodmenus/{menuName}").buildAndExpand(menuName).toUri();
                        item.addLink(new Link("menu", menuUri.toString()));
                        //MENUS LINK
                        URI menusUri = uriBuilder.path("/../../..").path("/foodmenus").build().toUri();
                        item.addLink(new Link("menus", menusUri.toString()));

                        return ResponseEntity.ok(item);

                    }
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Retrieves a specific food item from a given order by its name and the order ID.
     *
     * @param orderId  The ID of the order to search within.
     * @param itemName The name of the food item to retrieve (case and whitespace insensitive).
     * @return ResponseEntity containing the {@link FoodItem} if found in the order, or a 404 Not Found status if not.
     */
    @GetMapping(path = "/foodorders/{orderId}/{itemName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a food item from a specific order by name and order ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Food item found in order", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = FoodItem.class))),
            @ApiResponse(responseCode = "404", description = "Food item or order not found")
    })
    public ResponseEntity<FoodItem> getFoodItemFromOrder(
            @PathVariable int orderId,
            @PathVariable String itemName) {
        FoodOrders foodOrders = JSONMarshall.unmarshallFoodOrder("FoodOrders");
        if (foodOrders == null || foodOrders.getFoodOrderList() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (FoodOrder order : foodOrders.getFoodOrderList()) {
            if (order.getId() == orderId && order.getFoodItems() != null) {
                for (FoodItem item : order.getFoodItems()) {
                    if (normalizeName(item.getName()).equalsIgnoreCase(itemName)) {
                        // SELF LINK
                        UriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri();
                        URI selfUri = uriBuilder.build().toUri();
                        item.addLink(new Link("self", selfUri.toString()));
                        // ORDER LINK
                        URI orderUri = uriBuilder.path("/../../..").path("/foodorders/{orderId}").buildAndExpand(orderId).toUri();
                        item.addLink(new Link("order", orderUri.toString()));
                        // ORDERS LINK
                        URI ordersUri = uriBuilder.path("/../../..").path("/foodorders").build().toUri();
                        item.addLink(new Link("orders", ordersUri.toString()));

                        return ResponseEntity.ok(item);
                    }
                }
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}