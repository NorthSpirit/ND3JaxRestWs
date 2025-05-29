package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BaseController.class)
class BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRoot_shouldReturnOkAndWelcomeMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_HTML_VALUE))
                .andExpect(MockMvcResultMatchers.content().string(
                        "<html><body>" +
                                "<h1>Hello and Welcome to the Food Menu API thingy!</h1>" +
                                "<p>Available endpoints:</p>" +
                                "<ul>" +
                                "<li><a href='/foodmenus'>/foodmenus</a></li>" +
                                "<li><a href='/foodorders'>/foodorders</a></li>" +
                                "</ul>" +
                                "</body></html>"
                ));
    }
}