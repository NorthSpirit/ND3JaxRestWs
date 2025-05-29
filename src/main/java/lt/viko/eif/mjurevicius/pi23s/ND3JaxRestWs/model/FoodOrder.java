package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model;

import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server.Link;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer's food order.
 * This class contains details such as the order ID, total price, status,
 * customer name, timestamp of the order, a list of ordered food items,
 * and hypermedia links for API interaction.
 */
public class FoodOrder {

    /**
     * The unique identifier for the food order.
     */
    private int id;

    /**
     * The total price of the food order.
     */
    private double totalPrice;

    /**
     * The current status of the food order (e.g., "ordered", "preparing", "delivered").
     */
    private String status;

    /**
     * The name of the customer who placed the order.
     */
    private String customerName;

    /**
     * The date and time when the order was placed.
     */
    private LocalDateTime timeStamp;

    /**
     * A list of {@link FoodItem} objects included in this order.
     */
    private List<FoodItem> foodItems;

    /**
     * A list of hypermedia links associated with this food order, enabling clients
     * to navigate related resources.
     */
    public List<Link> links = new ArrayList<>();

    /**
     * Default constructor for creating a new FoodOrder instance.
     */
    public FoodOrder() {
    }

    /**
     * Gets the unique identifier of the food order.
     *
     * @return The order ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the food order.
     *
     * @param id The order ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the total price of the food order.
     *
     * @return The total price.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total price of the food order.
     *
     * @param totalPrice The total price to set.
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the current status of the food order.
     *
     * @return The order status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current status of the food order.
     *
     * @param status The order status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the name of the customer who placed the order.
     *
     * @return The customer's name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the name of the customer who placed the order.
     *
     * @param customerName The customer's name to set.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the timestamp of when the order was placed.
     *
     * @return The order timestamp.
     */
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the timestamp of when the order was placed.
     *
     * @param timeStamp The order timestamp to set.
     */
    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Gets the list of food items in this order.
     *
     * @return The list of ordered food items.
     */
    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    /**
     * Sets the list of food items for this order.
     *
     * @param foodItems The list of food items to set.
     */
    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    /**
     * Gets the list of hypermedia links associated with this food order.
     *
     * @return The list of links.
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * Sets the list of hypermedia links for the food order.
     *
     * @param links The list of links to set.
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * Adds a hypermedia link to the food order's list of links.
     *
     * @param link The link to add.
     */
    public void addLink(Link link) {
        this.links.add(link);
    }
}
