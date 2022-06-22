package org.example.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello_returned() throws Exception {

        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto_returned() throws Exception {

        String name = "hello";
        int amount = 1000;

        // GET 메소드로 "/hello/dto"를 전달했고 name 파라미터는 name("hello"), amount 파라미터는 amount(1000)
        mvc.perform(get("/hello/dto").param("name", name)
                                               .param("amount", String.valueOf(amount)))

                .andExpect(status().isOk()) // 정상적으로 동작했는지 확인
                .andExpect(jsonPath("$.name", is(name))) // 반환받은 HelloResponseDto 클래스의 내용의 name이 "hello"가 맞는지 확인
                .andExpect(jsonPath("$.amount", is(amount))); // 반환받은 HelloResponseDto 클래스의 내용의 amount가 1000이 맞는지 확인

    }

}
