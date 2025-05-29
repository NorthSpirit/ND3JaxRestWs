package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model;

import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server.Link;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodOrderTest {

    @Test
    void getId() {
        FoodOrder order = new FoodOrder();
        order.setId(1);
        assertEquals(1, order.getId());
    }

    @Test
    void setId() {
        FoodOrder order = new FoodOrder();
        order.setId(2);
        assertEquals(2, order.getId());
        order.setId(10);
        assertEquals(10, order.getId());
    }

    @Test
    void getTotalPrice() {
        FoodOrder order = new FoodOrder();
        order.setTotalPrice(27.49);
        assertEquals(27.49, order.getTotalPrice(), 0.001);
    }

    @Test
    void setTotalPrice() {
        FoodOrder order = new FoodOrder();
        order.setTotalPrice(15.99);
        assertEquals(15.99, order.getTotalPrice(), 0.001);
        order.setTotalPrice(30.00);
        assertEquals(30.00, order.getTotalPrice(), 0.001);
    }

    @Test
    void getStatus() {
        FoodOrder order = new FoodOrder();
        order.setStatus("ordered");
        assertEquals("ordered", order.getStatus());
    }

    @Test
    void setStatus() {
        FoodOrder order = new FoodOrder();
        order.setStatus("PROCESSING");
        assertEquals("PROCESSING", order.getStatus());
        order.setStatus("DELIVERED");
        assertEquals("DELIVERED", order.getStatus());
    }

    @Test
    void getCustomerName() {
        FoodOrder order = new FoodOrder();
        order.setCustomerName("Mantas Jurevicius");
        assertEquals("Mantas Jurevicius", order.getCustomerName());
    }

    @Test
    void setCustomerName() {
        FoodOrder order = new FoodOrder();
        order.setCustomerName("Alice Smith");
        assertEquals("Alice Smith", order.getCustomerName());
        order.setCustomerName("Bob Johnson");
        assertEquals("Bob Johnson", order.getCustomerName());
    }

    @Test
    void getTimeStamp() {
        LocalDateTime now = LocalDateTime.now();
        FoodOrder order = new FoodOrder();
        order.setTimeStamp(now);
        assertEquals(now, order.getTimeStamp());
    }

    @Test
    void setTimeStamp() {
        LocalDateTime time1 = LocalDateTime.of(2025, 4, 19, 13, 37);
        LocalDateTime time2 = LocalDateTime.now().plusHours(1);
        FoodOrder order = new FoodOrder();
        order.setTimeStamp(time1);
        assertEquals(time1, order.getTimeStamp());
        order.setTimeStamp(time2);
        assertEquals(time2, order.getTimeStamp());
    }

    @Test
    void getFoodItems() {
        FoodOrder order = new FoodOrder();
        List<FoodItem> foodItems = new ArrayList<>();
        order.setFoodItems(foodItems);
        assertEquals(foodItems, order.getFoodItems());
        assertNotNull(order.getFoodItems());
    }

    @Test
    void setFoodItems() {
        FoodOrder order = new FoodOrder();
        List<FoodItem> items1 = new ArrayList<>();
        FoodItem item1 = new FoodItem();
        item1.setName("Pizza");
        items1.add(item1);
        order.setFoodItems(items1);
        assertEquals(1, order.getFoodItems().size());
        assertEquals("Pizza", order.getFoodItems().get(0).getName());

        List<FoodItem> items2 = new ArrayList<>();
        FoodItem item2 = new FoodItem();
        item2.setName("Salad");
        items2.add(item2);
        order.setFoodItems(items2);
        assertEquals(1, order.getFoodItems().size());
        assertEquals("Salad", order.getFoodItems().get(0).getName());
    }

    @Test
    void getLinks() {
        FoodOrder order = new FoodOrder();
        List<Link> links = new ArrayList<>();
        order.setLinks(links);
        assertEquals(links, order.getLinks());
        assertNotNull(order.getLinks());
    }

    @Test
    void setLinks() {
        FoodOrder order = new FoodOrder();
        List<Link> links1 = new ArrayList<>();
        Link link1 = new Link("self", "/foodorders/1");
        links1.add(link1);
        order.setLinks(links1);
        assertEquals(1, order.getLinks().size());
        assertEquals("self", order.getLinks().get(0).getRel());

        List<Link> links2 = new ArrayList<>();
        Link link2 = new Link("items", "/foodorders/1/items");
        links2.add(link2);
        order.setLinks(links2);
        assertEquals(1, order.getLinks().size());
        assertEquals("items", order.getLinks().get(0).getRel());
    }

    @Test
    void addLink() {
        FoodOrder order = new FoodOrder();
        Link link1 = new Link("self", "/foodorders/1");
        order.addLink(link1);
        assertEquals(1, order.getLinks().size());
        assertEquals("self", order.getLinks().get(0).getRel());
        assertEquals("/foodorders/1", order.getLinks().get(0).getHref());

        Link link2 = new Link("items", "/foodorders/1/items");
        order.addLink(link2);
        assertEquals(2, order.getLinks().size());
        assertEquals("items", order.getLinks().get(1).getRel());
        assertEquals("/foodorders/1/items", order.getLinks().get(1).getHref());
    }
}