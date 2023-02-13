package com.touchbiz.chatgpt;

import com.touchbiz.chatgpt.boot.ChatgptApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(value = {"classpath:application.yml"}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ChatgptApplication.class)
@ActiveProfiles(profiles = "local")
@AutoConfigureMockMvc
@Transactional
@Import({FeignAutoConfiguration.class, HttpMessageConvertersAutoConfiguration.class})
public abstract class BaseTest {

    @LocalServerPort
    int randomServerPort;

}