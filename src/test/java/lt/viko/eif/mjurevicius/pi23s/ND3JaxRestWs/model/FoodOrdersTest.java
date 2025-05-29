package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model;

import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server.Link;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodOrdersTest {

    @Test
    void getFoodOrderList() {
        FoodOrders foodOrders = new FoodOrders();
        List<FoodOrder> orderList = new ArrayList<>();
        foodOrders.setFoodOrderList(orderList);
        assertEquals(orderList, foodOrders.getFoodOrderList());
        assertNotNull(foodOrders.getFoodOrderList());
    }

    @Test
    void setFoodOrderList() {
        FoodOrders foodOrders = new FoodOrders();
        List<FoodOrder> orderList1 = new ArrayList<>();
        FoodOrder order1 = new FoodOrder();
        order1.setCustomerName("Customer A");
        orderList1.add(order1);
        foodOrders.setFoodOrderList(orderList1);
        assertEquals(1, foodOrders.getFoodOrderList().size());
        assertEquals("Customer A", foodOrders.getFoodOrderList().get(0).getCustomerName());

        List<FoodOrder> orderList2 = new ArrayList<>();
        FoodOrder order2 = new FoodOrder();
        order2.setCustomerName("Customer B");
        orderList2.add(order2);
        foodOrders.setFoodOrderList(orderList2);
        assertEquals(1, foodOrders.getFoodOrderList().size());
        assertEquals("Customer B", foodOrders.getFoodOrderList().get(0).getCustomerName());
    }

    @Test
    void getLinks() {
        FoodOrders foodOrders = new FoodOrders();
        List<Link> links = new ArrayList<>();
        foodOrders.setLinks(links);
        assertEquals(links, foodOrders.getLinks());
        assertNotNull(foodOrders.getLinks());
    }

    @Test
    void setLinks() {
        FoodOrders foodOrders = new FoodOrders();
        List<Link> links1 = new ArrayList<>();
        Link link1 = new Link("self", "/foodorders");
        links1.add(link1);
        foodOrders.setLinks(links1);
        assertEquals(1, foodOrders.getLinks().size());
        assertEquals("self", foodOrders.getLinks().get(0).getRel());

        List<Link> links2 = new ArrayList<>();
        Link link2 = new Link("menu", "/foodmenus");
        links2.add(link2);
        foodOrders.setLinks(links2);
        assertEquals(1, foodOrders.getLinks().size());
        assertEquals("menu", foodOrders.getLinks().get(0).getRel());
    }

    @Test
    void addLink() {
        FoodOrders foodOrders = new FoodOrders();
        Link link1 = new Link("self", "/foodorders");
        foodOrders.addLink(link1);
        assertEquals(1, foodOrders.getLinks().size());
        assertEquals("self", foodOrders.getLinks().get(0).getRel());
        assertEquals("/foodorders", foodOrders.getLinks().get(0).getHref());

        Link link2 = new Link("menu", "/foodmenus");
        foodOrders.addLink(link2);
        assertEquals(2, foodOrders.getLinks().size());
        assertEquals("menu", foodOrders.getLinks().get(1).getRel());
        assertEquals("/foodmenus", foodOrders.getLinks().get(1).getHref());
    }
}