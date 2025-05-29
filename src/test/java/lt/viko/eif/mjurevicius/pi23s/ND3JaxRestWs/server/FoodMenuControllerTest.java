package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class FoodMenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllMenus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/foodmenus")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.foodMenuList", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.foodMenuList[0].menuName", is("Breakfast")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.foodMenuList[0].menuItems", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.foodMenuList[0].menuItems[0].name", is("Pancakes")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.foodMenuList[0].menuItems[1].name", is("Omelette")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.foodMenuList[0].links", hasSize(2)));
    }

    @Test
    void getMenuByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/foodmenus/Breakfast")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.menuName", is("Breakfast")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.menuItems", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.menuItems[0].name", is("Pancakes")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.menuItems[1].name", is("Omelette")));
    }

    @Test
    void getMenuByName_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/foodmenus/Dinner")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}