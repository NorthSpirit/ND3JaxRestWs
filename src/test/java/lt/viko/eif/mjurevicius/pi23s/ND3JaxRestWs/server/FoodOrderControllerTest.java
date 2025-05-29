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
public class FoodOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllOrders_shouldReturnCorrectDataAndLinks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/foodorders")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.foodOrderList", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.foodOrderList[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.foodOrderList[0].totalPrice", is(25.98)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.foodOrderList[0].status", is("PLACED")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.foodOrderList[0].customerName", is("Test Customer")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.foodOrderList[0].foodItems", hasSize(2)));
    }

    @Test
    void getOrderById_existingId_shouldReturnCorrectOrderAndLinks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/foodorders/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice", is(25.98)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", is("PLACED")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerName", is("Test Customer")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.foodItems", hasSize(2)));
    }

    @Test
    void getOrderById_nonExistingId_shouldReturnNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/foodorders/99")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}