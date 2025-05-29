package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.services;

import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model.FoodMenus;
import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model.FoodOrders;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JSONMarshallTest {

    @Test
    void unmarshallFoodOrder_success() {
        FoodOrders foodOrders = JSONMarshall.unmarshallFoodOrder("FoodOrders.json");
        assertNotNull(foodOrders);
        assertNotNull(foodOrders.getFoodOrderList());
        assertEquals(1, foodOrders.getFoodOrderList().size());

        var firstOrder = foodOrders.getFoodOrderList().get(0);
        assertEquals(1, firstOrder.getId());
        assertEquals(25.98, firstOrder.getTotalPrice(), 0.001);
        assertEquals("PLACED", firstOrder.getStatus());
        assertEquals("Test Customer", firstOrder.getCustomerName());
        assertNotNull(firstOrder.getTimeStamp());
        assertNotNull(firstOrder.getFoodItems());
        assertEquals(2, firstOrder.getFoodItems().size());
        assertNotNull(foodOrders.getLinks());
        assertEquals(1, foodOrders.getLinks().size());
        assertEquals("self", foodOrders.getLinks().get(0).getRel());
        assertEquals("/foodorders", foodOrders.getLinks().get(0).getHref());
    }

    @Test
    void unmarshallFoodOrder_fileNotFound() {
        FoodOrders foodOrders = JSONMarshall.unmarshallFoodOrder("nonExistentFile.json");
        assertNull(foodOrders);
    }

    @Test
    void unmarshallFoodOrder_invalidJson() {
        // Create an invalid JSON file in src/test/resources
        String invalidJsonFile = "invalidFoodOrders.json";
        try {
            java.io.File file = new java.io.File("src/test/resources/" + invalidJsonFile);
            java.nio.file.Files.writeString(file.toPath(), "{ \"foodOrderList\": [ { \"id\": 1, } ]", java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.TRUNCATE_EXISTING);
            FoodOrders foodOrders = JSONMarshall.unmarshallFoodOrder(invalidJsonFile);
            assertNull(foodOrders);
            java.nio.file.Files.deleteIfExists(file.toPath()); // Clean up the invalid file
        } catch (java.io.IOException e) {
            fail("Failed to create invalid JSON file: " + e.getMessage());
        }
    }

    @Test
    void unmashallFoodMenu_success() {
        FoodMenus foodMenus = JSONMarshall.unmashallFoodMenu("FoodMenus.json");
        assertNotNull(foodMenus);
        assertNotNull(foodMenus.getFoodMenuList());
        assertEquals(1, foodMenus.getFoodMenuList().size());

        var firstMenu = foodMenus.getFoodMenuList().get(0);
        assertEquals("Breakfast", firstMenu.getMenuName());
        assertNotNull(firstMenu.getMenuItems());
        assertEquals(2, firstMenu.getMenuItems().size());
        assertEquals("Pancakes", firstMenu.getMenuItems().get(0).getName());
        assertEquals("Omelette", firstMenu.getMenuItems().get(1).getName());
        assertNotNull(firstMenu.getLinks());
        assertEquals(1, firstMenu.getLinks().size());
        assertEquals("self", firstMenu.getLinks().get(0).getRel());
        assertEquals("/foodmenus/breakfast", firstMenu.getLinks().get(0).getHref());
        assertNotNull(foodMenus.getLinks());
        assertEquals(1, foodMenus.getLinks().size());
        assertEquals("self", foodMenus.getLinks().get(0).getRel());
        assertEquals("/foodmenus", foodMenus.getLinks().get(0).getHref());
    }

    @Test
    void unmashallFoodMenu_fileNotFound() {
        FoodMenus foodMenus = JSONMarshall.unmashallFoodMenu("nonExistentMenuFile.json");
        assertNull(foodMenus);
    }

    @Test
    void unmashallFoodMenu_invalidJson() {
        // Create an invalid JSON file in src/test/resources
        String invalidJsonFile = "invalidFoodMenus.json";
        try {
            java.io.File file = new java.io.File("src/test/resources/" + invalidJsonFile);
            java.nio.file.Files.writeString(file.toPath(), "{ \"foodMenuList\": [ { \"menuName\": \"Lunch\", } ]", java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.TRUNCATE_EXISTING);
            FoodMenus foodMenus = JSONMarshall.unmashallFoodMenu(invalidJsonFile);
            assertNull(foodMenus);
            java.nio.file.Files.deleteIfExists(file.toPath()); // Clean up the invalid file
        } catch (java.io.IOException e) {
            fail("Failed to create invalid JSON file: " + e.getMessage());
        }
    }
}