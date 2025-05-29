package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model;

import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server.Link;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodItemTest {

    @Test
    void getName() {
        FoodItem item = new FoodItem();
        item.setName("Burger");
        assertEquals("Burger", item.getName());
    }

    @Test
    void setName() {
        FoodItem item = new FoodItem();
        item.setName("Fries");
        assertEquals("Fries", item.getName());
        item.setName("Soda");
        assertEquals("Soda", item.getName());
    }

    @Test
    void getPrice() {
        FoodItem item = new FoodItem();
        item.setPrice(5.99);
        assertEquals(5.99, item.getPrice(), 0.001);
    }

    @Test
    void setPrice() {
        FoodItem item = new FoodItem();
        item.setPrice(7.50);
        assertEquals(7.50, item.getPrice(), 0.001);
        item.setPrice(9.00);
        assertEquals(9.00, item.getPrice(), 0.001);
    }

    @Test
    void getVat() {
        FoodItem item = new FoodItem();
        item.setVat(0.21f);
        assertEquals(0.21f, item.getVat(), 0.001f);
    }

    @Test
    void setVat() {
        FoodItem item = new FoodItem();
        item.setVat(0.09f);
        assertEquals(0.09f, item.getVat(), 0.001f);
        item.setVat(0.18f);
        assertEquals(0.18f, item.getVat(), 0.001f);
    }

    @Test
    void getFullPrice() {
        FoodItem item = new FoodItem();
        item.setFullPrice(10.50);
        assertEquals(10.50, item.getFullPrice(), 0.001);
    }

    @Test
    void setFullPrice() {
        FoodItem item = new FoodItem();
        item.setFullPrice(12.75);
        assertEquals(12.75, item.getFullPrice(), 0.001);
        item.setFullPrice(15.00);
        assertEquals(15.00, item.getFullPrice(), 0.001);
    }

    @Test
    void getIngredients() {
        FoodItem item = new FoodItem();
        List<FoodIngredient> ingredients = new ArrayList<>();
        item.setIngredients(ingredients);
        assertEquals(ingredients, item.getIngredients());
        assertNotNull(item.getIngredients());
    }

    @Test
    void setIngredients() {
        FoodItem item = new FoodItem();
        List<FoodIngredient> ingredients1 = new ArrayList<>();
        FoodIngredient ingredient1 = new FoodIngredient();
        ingredient1.setName("Tomato");
        ingredients1.add(ingredient1);
        item.setIngredients(ingredients1);
        assertEquals(1, item.getIngredients().size());
        assertEquals("Tomato", item.getIngredients().get(0).getName());

        List<FoodIngredient> ingredients2 = new ArrayList<>();
        FoodIngredient ingredient2 = new FoodIngredient();
        ingredient2.setName("Cheese");
        ingredients2.add(ingredient2);
        item.setIngredients(ingredients2);
        assertEquals(1, item.getIngredients().size());
        assertEquals("Cheese", item.getIngredients().get(0).getName());
    }

    @Test
    void getLinks() {
        FoodItem item = new FoodItem();
        List<Link> links = new ArrayList<>();
        item.setLinks(links);
        assertEquals(links, item.getLinks());
        assertNotNull(item.getLinks());
    }

    @Test
    void setLinks() {
        FoodItem item = new FoodItem();
        List<Link> links1 = new ArrayList<>();
        Link link1 = new Link("self", "/items/1");
        links1.add(link1);
        item.setLinks(links1);
        assertEquals(1, item.getLinks().size());
        assertEquals("self", item.getLinks().get(0).getRel());

        List<Link> links2 = new ArrayList<>();
        Link link2 = new Link("menu", "/menus/pizza");
        links2.add(link2);
        item.setLinks(links2);
        assertEquals(1, item.getLinks().size());
        assertEquals("menu", item.getLinks().get(0).getRel());
    }

    @Test
    void addLink() {
        FoodItem item = new FoodItem();
        Link link1 = new Link("self", "/items/1");
        item.addLink(link1);
        assertEquals(1, item.getLinks().size());
        assertEquals("self", item.getLinks().get(0).getRel());
        assertEquals("/items/1", item.getLinks().get(0).getHref());

        Link link2 = new Link("menu", "/menus/pizza");
        item.addLink(link2);
        assertEquals(2, item.getLinks().size());
        assertEquals("menu", item.getLinks().get(1).getRel());
        assertEquals("/menus/pizza", item.getLinks().get(1).getHref());
    }
}