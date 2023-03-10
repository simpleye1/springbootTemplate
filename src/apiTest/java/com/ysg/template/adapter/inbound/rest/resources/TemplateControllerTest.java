package com.ysg.servicetemplate.api.inbound.rest.resources;

import com.ysg.servicetemplate.ApiBaseControllerTest;
import com.ysg.servicetemplate.api.request.CreateTemplateItemRequest;
import com.ysg.servicetemplate.api.response.CreateTempalteItemResponse;
import com.ysg.servicetemplate.infrastructure.repository.TemplateItemJpaRepository;
import com.ysg.servicetemplate.infrastructure.po.TemplateItemPO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("api-test")
public class TemplateControllerTest extends ApiBaseControllerTest {
    @Autowired
    private TemplateItemJpaRepository templateItemJpaRepository;

    @BeforeEach
    public void setUp() {
        templateItemJpaRepository.deleteAll();
    }

    @Test
    public void createItem_should_success() {
        CreateTemplateItemRequest request = CreateTemplateItemRequest.builder().tempKey("tempKey").tempValue("value").build();

        CreateTempalteItemResponse response = given().body(request)
                .post("/template/items")
                .then()
                .statusCode(200)
                .extract()
                .as(CreateTempalteItemResponse.class);

        assertThat(response.getId()).isNotNull();

        TemplateItemPO itemPO = templateItemJpaRepository.findById(response.getId()).get();
        assertThat(itemPO.getTempKey()).isEqualTo(request.getTempKey());
        assertThat(itemPO.getTempValue()).isEqualTo(request.getTempValue());
    }
}
