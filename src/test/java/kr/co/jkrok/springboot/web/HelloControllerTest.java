package kr.co.jkrok.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void return_Hello() {
        String hello = "hello";

        try {
            mvc.perform(get("/hello"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(hello));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void return_Dto() {
        String name = "hello";
        int amount = 1000;

        try {
            mvc.perform(
                    get("/hello/dto")
                    .param("name", name)
                    .param("amount",String.valueOf(amount)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(name)))
                    .andExpect(jsonPath("$amount", is(amount)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}