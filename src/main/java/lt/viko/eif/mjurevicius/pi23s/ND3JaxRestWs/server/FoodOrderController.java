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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for managing food orders.
 * Provides endpoints to retrieve all orders, a specific order by ID, and to create new orders.
 */
@RestController
@RequestMapping("/foodorders")
@Tag(name = "Controller for Food Order", description = "Endpoints for food orders")
public class FoodOrderController {

    /**
     * Normalizes a given food item name to lowercase and removes all whitespace.
     * Used for case-insensitive and whitespace-insensitive matching in URL paths.
     *
     * @param name The original food item name string.
     * @return A normalized version of the name.
     */
    private String normalizeName(String name) {
        return name.toLowerCase().replaceAll("\\s+", "");
    }

    /**
     * Retrieves all existing food orders.
     *
     * @return ResponseEntity containing a {@link FoodOrders} object with a list of all orders,
     * or a 500 Internal Server Error if there's an issue unmarshalling the data.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all food orders")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of all orders", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = FoodOrders.class)))
    public ResponseEntity<FoodOrders> getAllOrders() {
        FoodOrders foodOrders = JSONMarshall.unmarshallFoodOrder("FoodOrders");

        if (foodOrders == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        UriComponentsBuilder baseUriBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        //SELF LINK
        URI selfUri = baseUriBuilder.build().toUri();
        foodOrders.addLink(new Link("self", selfUri.toString()));

        //LINKS TO ORDERS AND FOOD ITEMS
        if (foodOrders.getFoodOrderList() != null) {
            for (FoodOrder order : foodOrders.getFoodOrderList()) {
                //ORDER LINK
                URI orderUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/foodorders/{id}")
                        .buildAndExpand(order.getId())
                        .toUri();
                order.addLink(new Link("order", orderUri.toString()));

                //FOODITEM LINK
                if (order.getFoodItems() != null) {
                    for (FoodItem item : order.getFoodItems()) {
                        URI itemUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/foodorders/{orderId}/{itemName}")
                                .buildAndExpand(order.getId(), normalizeName(item.getName()))
                                .toUri();
                        item.addLink(new Link("item", itemUri.toString()));
                    }
                }
            }
        }

        return ResponseEntity.ok(foodOrders);
    }

    /**
     * Retrieves a specific food order by its unique ID.
     *
     * @param id The ID of the food order to retrieve.
     * @return ResponseEntity containing the {@link FoodOrder} if found, with hypermedia links,
     * or a 404 Not Found status if the order does not exist.
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a food order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order retrived successfully.", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = FoodOrder.class))),
            @ApiResponse(responseCode = "404", description = "Place order not found")
    })
    public ResponseEntity<FoodOrder> getOrderById(@PathVariable int id) {
        FoodOrders foodOrders = JSONMarshall.unmarshallFoodOrder("FoodOrders");

        if (foodOrders != null && foodOrders.getFoodOrderList() != null) {
            return foodOrders.getFoodOrderList().stream()
                    .filter(order -> order.getId() == id)
                    .findFirst()
                    .map(order -> {
                        UriComponentsBuilder UriBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri();
                        String basePath = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/..").build().toUriString();
                        //SELF URI
                        URI selfUri = UriBuilder.build().toUri();
                        order.addLink(new Link("self", selfUri.toString()));

                        //URI TO ORDERS
                        URI ordersUri = UriBuilder.path("/../..").path("/foodorders").build().toUri();
                        order.addLink(new Link("orders", ordersUri.toString()));

                        //ITEMS LINKS
                        if (order.getFoodItems() != null) {
                            for (FoodItem item : order.getFoodItems()) {
                                URI itemUri = URI.create(basePath + "/" + id + "/" + normalizeName(item.getName()));
                                item.addLink(new Link("item", itemUri.toString()));
                            }
                        }

                        return ResponseEntity.ok(order);
                    })
                    .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Creates a new food order based on the provided JSON request body.
     * The total price of the order can be explicitly provided; if it's zero or negative,
     * it will be calculated as the sum of the full prices of the ordered food items.
     *
     * @param foodOrder The {@link FoodOrder} object from the request body.
     * @return ResponseEntity containing the created {@link FoodOrder} with a 201 Created status
     * and the URI of the new resource in the Location header, or a 400 Bad Request if the
     * order is invalid, or a 500 Internal Server Error if there's an issue loading or saving data.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new food order")
    @Tag(name = "Order creation service", description = "Creates an order, based on the data provider, if total price is a positive number, price gets taken for order total, if negative - counts price from food item prices.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created successfully.", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = FoodOrder.class))),
            @ApiResponse(responseCode = "400", description = "Order failed - Invalid request body."),
            @ApiResponse(responseCode = "500", description = "Error loading the data storage. Or Error saving the new order."),
    })
    public ResponseEntity<FoodOrder> createOrder(@RequestBody FoodOrder foodOrder) {

        if (foodOrder == null) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading the data storage.");
        }

        if (foodOrder.getFoodItems() == null || foodOrder.getFoodItems().isEmpty()) {
            ResponseEntity.badRequest().body("Order failed - Invalid request body. No ordered items found.");
        }

        FoodOrders foodOrders = JSONMarshall.unmarshallFoodOrder("FoodOrders");
        List<FoodOrder> orderList = (foodOrders != null && foodOrders.getFoodOrderList() != null)
                ? foodOrders.getFoodOrderList()
                : new ArrayList<>();
        foodOrders = (foodOrders != null) ? foodOrders : new FoodOrders();
        foodOrders.setFoodOrderList(orderList);

        int nextId = orderList.stream().mapToInt(FoodOrder::getId).max().orElse(0) + 1;
        foodOrder.setId(nextId);

        //PAYMENT PART
        if (foodOrder.getTotalPrice() <= 0)
        {
            double calculatedTotalPrice = 0.0;
            for (FoodItem foodItem : foodOrder.getFoodItems()) {
                calculatedTotalPrice += foodItem.getFullPrice();
            }
            foodOrder.setTotalPrice(calculatedTotalPrice);
        }

        orderList.add(foodOrder);

        boolean writeSuccessful = JSONMarshall.writeJsonToFile(foodOrders, "FoodOrders");
        if (!writeSuccessful) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving the new order.");
        }

        UriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}");
        URI location = uriBuilder.buildAndExpand(nextId).toUri();
        foodOrder.addLink(new Link("self", location.toString()));

        UriComponentsBuilder ordersUriBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/..");
        URI ordersUri = ordersUriBuilder.build().toUri();
        foodOrder.addLink(new Link("orders", ordersUri.toString()));

        return ResponseEntity.created(location).body(foodOrder);
    }
}
