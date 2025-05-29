package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model;

import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server.Link;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single food item available on a menu.
 * This class includes details such as the name, base price, VAT rate, full price,
 * a list of ingredients, and hypermedia links for API interaction.
 */
public class FoodItem {

    /**
     * The name of the food item, for example, "Pizza Margherita" or "Beef Steak".
     */
    private String name;

    /**
     * The base price of the food item, excluding VAT.
     */
    private double price;

    /**
     * The Value Added Tax (VAT) rate applicable to the food item.
     */
    private float vat;

    /**
     * The full price of the food item, including VAT.
     */
    private double fullPrice;

    /**
     * A list of ingredients that constitute this food item. Each ingredient is detailed
     * by its name, measurement unit, and quantity.
     */
    private List<FoodIngredient> ingredients;

    /**
     * A list of hypermedia links associated with this food item, enabling clients
     * to discover related resources and actions.
     */
    public List<Link> links = new ArrayList<>();

    public FoodItem() {
    }

    /**
     * Default constructor for creating a new FoodItem instance.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the name of the food item.
     *
     * @return The name of the food item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the base price of the food item.
     *
     * @return The base price of the food item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the base price of the food item.
     *
     * @param price The price to set for the food item.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the VAT rate of the food item.
     *
     * @return The VAT rate.
     */
    public float getVat() {
        return vat;
    }

    /**
     * Sets the VAT rate of the food item.
     *
     * @param vat The VAT rate to set for the food item.
     */
    public void setVat(float vat) {
        this.vat = vat;
    }

    /**
     * Gets the full price of the food item, including VAT.
     *
     * @return The full price of the food item.
     */
    public double getFullPrice() {
        return fullPrice;
    }

    /**
     * Sets the full price of the food item.
     *
     * @param fullPrice The full price to set for the food item.
     */
    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    /**
     * Gets the list of ingredients for the food item.
     *
     * @return The list of ingredients.
     */
    public List<FoodIngredient> getIngredients() {
        return ingredients;
    }

    /**
     * Sets the list of ingredients for the food item.
     *
     * @param ingredients The list of ingredients to set.
     */
    public void setIngredients(List<FoodIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Gets the list of hypermedia links associated with the food item.
     *
     * @return The list of links.
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * Sets the list of hypermedia links for the food item.
     *
     * @param links The list of links to set.
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * Adds a hypermedia link to the food item's list of links.
     *
     * @param link The link to add.
     */
    public void addLink(Link link) {
        this.links.add(link);
    }
}
