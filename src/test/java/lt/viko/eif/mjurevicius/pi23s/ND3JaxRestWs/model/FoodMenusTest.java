package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model;

import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server.Link;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodMenusTest {

    @Test
    void getFoodMenuList() {
        FoodMenus foodMenus = new FoodMenus();
        List<FoodMenu> menuList = new ArrayList<>();
        foodMenus.setFoodMenuList(menuList);
        assertEquals(menuList, foodMenus.getFoodMenuList());
        assertNotNull(foodMenus.getFoodMenuList());
    }

    @Test
    void setFoodMenuList() {
        FoodMenus foodMenus = new FoodMenus();
        List<FoodMenu> menuList1 = new ArrayList<>();
        FoodMenu menu1 = new FoodMenu();
        menu1.setMenuName("Breakfast");
        menuList1.add(menu1);
        foodMenus.setFoodMenuList(menuList1);
        assertEquals(1, foodMenus.getFoodMenuList().size());
        assertEquals("Breakfast", foodMenus.getFoodMenuList().get(0).getMenuName());

        List<FoodMenu> menuList2 = new ArrayList<>();
        FoodMenu menu2 = new FoodMenu();
        menu2.setMenuName("Lunch");
        menuList2.add(menu2);
        foodMenus.setFoodMenuList(menuList2);
        assertEquals(1, foodMenus.getFoodMenuList().size());
        assertEquals("Lunch", foodMenus.getFoodMenuList().get(0).getMenuName());
    }

    @Test
    void getLinks() {
        FoodMenus foodMenus = new FoodMenus();
        List<Link> links = new ArrayList<>();
        foodMenus.setLinks(links);
        assertEquals(links, foodMenus.getLinks());
        assertNotNull(foodMenus.getLinks());
    }

    @Test
    void setLinks() {
        FoodMenus foodMenus = new FoodMenus();
        List<Link> links1 = new ArrayList<>();
        Link link1 = new Link("self", "/foodmenus");
        links1.add(link1);
        foodMenus.setLinks(links1);
        assertEquals(1, foodMenus.getLinks().size());
        assertEquals("self", foodMenus.getLinks().get(0).getRel());
    }

    @Test
    void addLink() {
        FoodMenus foodMenus = new FoodMenus();
        Link link1 = new Link("self", "/foodmenus");
        foodMenus.addLink(link1);
        assertEquals(1, foodMenus.getLinks().size());
        assertEquals("self", foodMenus.getLinks().get(0).getRel());
        assertEquals("/foodmenus", foodMenus.getLinks().get(0).getHref());
    }
}