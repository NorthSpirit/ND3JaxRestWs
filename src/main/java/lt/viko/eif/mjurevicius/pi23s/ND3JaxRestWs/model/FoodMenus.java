package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model;

import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server.Link;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a collection of food menus.
 * This class is used to encapsulate a list of {@link FoodMenu} objects and includes
 * hypermedia links for accessing the collection itself or related resources.
 */
public class FoodMenus {

    /**
     * A list containing multiple {@link FoodMenu} objects. This represents the collection
     * of available food menus.
     */
    private List<FoodMenu> foodMenuList;

    /**
     * A list of hypermedia links associated with this collection of food menus, enabling
     * clients to navigate and interact with the resource.
     */
    public List<Link> links = new ArrayList<>();

    /**
     * Default constructor for creating a new FoodMenus instance.
     */
    public FoodMenus() {
    }

    /**
     * Gets the list of food menus.
     *
     * @return The list of {@link FoodMenu} objects.
     */
    public List<FoodMenu> getFoodMenuList() {
        return foodMenuList;
    }

    /**
     * Sets the list of food menus.
     *
     * @param foodMenuList The list of {@link FoodMenu} objects to set.
     */
    public void setFoodMenuList(List<FoodMenu> foodMenuList) {
        this.foodMenuList = foodMenuList;
    }

    /**
     * Gets the list of hypermedia links associated with this collection of food menus.
     *
     * @return The list of links.
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * Sets the list of hypermedia links for this collection of food menus.
     *
     * @param links The list of links to set.
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * Adds a hypermedia link to the list of links for this collection of food menus.
     *
     * @param link The link to add.
     */
    public void addLink(Link link) {
        this.links.add(link);
    }
}
