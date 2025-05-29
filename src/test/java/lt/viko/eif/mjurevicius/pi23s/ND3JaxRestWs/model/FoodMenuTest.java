package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model;

import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server.Link;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodMenuTest {

    @Test
    void getMenuName() {
        FoodMenu menu = new FoodMenu();
        menu.setMenuName("salads");
        assertEquals("salads", menu.getMenuName());
    }

    @Test
    void setMenuName() {
        FoodMenu menu = new FoodMenu();
        menu.setMenuName("cold soups");
        assertEquals("cold soups", menu.getMenuName());
        menu.setMenuName("desserts");
        assertEquals("desserts", menu.getMenuName());
    }

    @Test
    void getMenuDescription() {
        FoodMenu menu = new FoodMenu();
        menu.setMenuDescription("A selection of fresh salads");
        assertEquals("A selection of fresh salads", menu.getMenuDescription());
    }

    @Test
    void setMenuDescription() {
        FoodMenu menu = new FoodMenu();
        menu.setMenuDescription("Selection of cold soups, often mashed.");
        assertEquals("Selection of cold soups, often mashed.", menu.getMenuDescription());
        menu.setMenuDescription("Sweet treats to end your meal");
        assertEquals("Sweet treats to end your meal", menu.getMenuDescription());
    }

    @Test
    void getMenuPicPath() {
        FoodMenu menu = new FoodMenu();
        menu.setMenuPicPath("/images/restaurant");
        assertEquals("/images/restaurant", menu.getMenuPicPath());
    }

    @Test
    void setMenuPicPath() {
        FoodMenu menu = new FoodMenu();
        menu.setMenuPicPath("/images/v2/");
        assertEquals("/images/v2/", menu.getMenuPicPath());
    }

    @Test
    void getMenuPicName() {
        FoodMenu menu = new FoodMenu();
        menu.setMenuPicName("salad.jpg");
        assertEquals("salad.jpg", menu.getMenuPicName());
    }

    @Test
    void setMenuPicName() {
        FoodMenu menu = new FoodMenu();
        menu.setMenuPicName("soup.png");
        assertEquals("soup.png", menu.getMenuPicName());
        menu.setMenuPicName("cake.gif");
        assertEquals("cake.gif", menu.getMenuPicName());
    }

    @Test
    void getMenuItems() {
        FoodMenu menu = new FoodMenu();
        List<FoodItem> menuItems = new ArrayList<>();
        menu.setMenuItems(menuItems);
        assertEquals(menuItems, menu.getMenuItems());
        assertNotNull(menu.getMenuItems());
    }

    @Test
    void setMenuItems() {
        FoodMenu menu = new FoodMenu();
        List<FoodItem> items1 = new ArrayList<>();
        FoodItem item1 = new FoodItem();
        item1.setName("Caesar Salad");
        items1.add(item1);
        menu.setMenuItems(items1);
        assertEquals(1, menu.getMenuItems().size());
        assertEquals("Caesar Salad", menu.getMenuItems().get(0).getName());

        List<FoodItem> items2 = new ArrayList<>();
        FoodItem item2 = new FoodItem();
        item2.setName("Tomato Soup");
        items2.add(item2);
        menu.setMenuItems(items2);
        assertEquals(1, menu.getMenuItems().size());
        assertEquals("Tomato Soup", menu.getMenuItems().get(0).getName());
    }

    @Test
    void getLinks() {
        FoodMenu menu = new FoodMenu();
        List<Link> links = new ArrayList<>();
        menu.setLinks(links);
        assertEquals(links, menu.getLinks());
        assertNotNull(menu.getLinks());
    }

    @Test
    void setLinks() {
        FoodMenu menu = new FoodMenu();
        List<Link> links1 = new ArrayList<>();
        Link link1 = new Link("item", "/menus/maindishes/vegetarianrisotto");
        links1.add(link1);
        menu.setLinks(links1);
        assertEquals(1, menu.getLinks().size());
        assertEquals("item", menu.getLinks().get(0).getRel());

        List<Link> links2 = new ArrayList<>();
        Link link2 = new Link("item", "/menus/maindishes/lambchops");
        links2.add(link2);
        menu.setLinks(links2);
        assertEquals(1, menu.getLinks().size());
        assertEquals("item", menu.getLinks().get(0).getRel());
    }

    @Test
    void addLink() {
        FoodMenu menu = new FoodMenu();
        menu.setMenuName("maindishes"); // Setting a menu name for the link test
        Link selfLink = new Link("self", "/foodmenus/maindishes");
        menu.addLink(selfLink);
        assertEquals(1, menu.getLinks().size());
        assertEquals("self", menu.getLinks().get(0).getRel());
        assertEquals("/foodmenus/maindishes", menu.getLinks().get(0).getHref());

        Link imageLink = new Link("image", "/foodmenus/maindishes/image");
        menu.addLink(imageLink);
        assertEquals(2, menu.getLinks().size());
        assertEquals("image", menu.getLinks().get(1).getRel());
        assertEquals("/foodmenus/maindishes/image", menu.getLinks().get(1).getHref());
    }
}