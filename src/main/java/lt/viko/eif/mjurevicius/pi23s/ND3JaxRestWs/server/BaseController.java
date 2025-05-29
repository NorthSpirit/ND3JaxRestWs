package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for handling the root path of the API.
 * This controller provides a basic welcome message with links to the main API endpoints.
 */
@RestController
@Tag(name = "Controller for the welcoming page", description = "Not really meant for the human client to see.")
public class BaseController {

    /**
     * Handles GET requests to the root path ("/").
     * It returns an HTML page with a welcome message and links to the /foodmenus and /foodorders endpoints.
     *
     * @return A ResponseEntity containing an HTML string with the welcome message and links.
     */
    @GetMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
    @Operation(summary = "Get the welcome page.")
    public ResponseEntity<String> getRoot() {
        String message = "<html><body>" +
                "<h1>Hello and Welcome to the Food Menu API thingy!</h1>" +
                "<p>Available endpoints:</p>" +
                "<ul>" +
                "<li><a href='/foodmenus'>/foodmenus</a></li>" +
                "<li><a href='/foodorders'>/foodorders</a></li>" +
                "</ul>" +
                "</body></html>";
        return ResponseEntity.ok(message);
    }
}
