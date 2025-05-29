package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model;

import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server.Link;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a menu containing a list of food items.
 * This class includes the menu's name, a short description, the path and name
 * of an associated picture, the list of food items it contains, and hypermedia links.
 */
public class FoodMenu {

    /**
     * The name of the food menu, for example, "Main Dishes" or "Desserts".
     */
    private String menuName;

    /**
     * A short description of the food menu's contents or theme.
     */
    private String menuDescription;

    /**
     * The file path where the menu's picture is located.
     */
    private String menuPicPath;

    /**
     * The name of the picture file associated with the menu, requires extension.
     */
    private String menuPicName;

    /**
     * A list of FoodItem objects that are part of this menu.
     */
    private List<FoodItem> menuItems;

    /**
     * A list of hypermedia links associated with this food menu, enabling clients
     * to navigate related resources.
     */
    public List<Link> links = new ArrayList<>();

    /**
     * Default constructor for creating a new FoodMenu instance.
     */
    public FoodMenu() {
    }

    /**
     * Gets the name of the food menu.
     *
     * @return The name of the menu.
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * Sets the name of the food menu.
     *
     * @param menuName The name to set for the menu.
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * Gets the description of the food menu.
     *
     * @return The description of the menu.
     */
    public String getMenuDescription() {
        return menuDescription;
    }

    /**
     * Sets the description of the food menu.
     *
     * @param menuDescription The description to set for the menu.
     */
    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    /**
     * Gets the file path of the menu's picture.
     *
     * @return The path to the menu's picture.
     */
    public String getMenuPicPath() {
        return menuPicPath;
    }

    /**
     * Sets the file path of the menu's picture.
     *
     * @param menuPicPath The path to set for the menu's picture.
     */
    public void setMenuPicPath(String menuPicPath) {
        this.menuPicPath = menuPicPath;
    }

    /**
     * Gets the name of the menu's picture file.
     *
     * @return The name of the menu's picture file.
     */
    public String getMenuPicName() {
        return menuPicName;
    }

    /**
     * Sets the name of the menu's picture file.
     *
     * @param menuPicName The name to set for the menu's picture file.
     */
    public void setMenuPicName(String menuPicName) {
        this.menuPicName = menuPicName;
    }

    /**
     * Gets the list of food items in this menu.
     *
     * @return The list of food items.
     */
    public List<FoodItem> getMenuItems() {
        return menuItems;
    }

    /**
     * Sets the list of food items for this menu.
     *
     * @param menuItems The list of food items to set.
     */
    public void setMenuItems(List<FoodItem> menuItems) {
        this.menuItems = menuItems;
    }

    /**
     * Gets the list of hypermedia links associated with this food menu.
     *
     * @return The list of links.
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * Sets the list of hypermedia links for the food menu.
     *
     * @param links The list of links to set.
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * Adds a hypermedia link to the food menu's list of links.
     *
     * @param link The link to add.
     */
    public void addLink(Link link) {
        this.links.add(link);
    }
}
