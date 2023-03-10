package com.ysg.servicetemplate;

import com.ysg.servicetemplate.common.general.event.EventPublisher;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.apache.groovy.util.Maps;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public abstract class ApiBaseControllerTest {


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EventPublisher eventPublisher;

    @BeforeEach
    protected void baseSetUp() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        eventPublisher.setApplicationEventPublisher(webApplicationContext);
    }

    protected MockMvcRequestSpecification given() {
        return RestAssuredMockMvc
                .given()
                .header("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE)
                .header("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
                .header("current-user-id","current-user-id")
                ;
    }


    private MockMvcRequestSpecification requestWithParams(Map<String, Object> m1, Map... map) {
        if (m1 == null) {
            m1 = new HashMap<String, Object>();
        }
        HashMap maps = new HashMap<Object, Object>(m1);
        for (Map value : map) {
            maps.putAll(value);
        }
        return given().params(maps);
    }

    protected MockMvcRequestSpecification requestWithParams(Map... map) {
        return requestWithParams(null, map);
    }

    protected MockMvcRequestSpecification requestPageParams(int currentPage, int pageSize, Map... map) {
        Map<String, Object> pageInfo = Maps.of("page", currentPage, "size", pageSize);
        return requestWithParams(pageInfo, map);
    }

    protected MockMvcRequestSpecification requestWithBody(Object object) {
        return given().body(object);
    }


}
