package com.hendisantika.springbootapikeyexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-apikey-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/31/23
 * Time: 05:56
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest
@AutoConfigureMockMvc
class GreetingsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Value("${application.security.api-key}")
    private String apiKey;

}
