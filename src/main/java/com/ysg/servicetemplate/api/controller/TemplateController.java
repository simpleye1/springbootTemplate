package com.ysg.servicetemplate.api.controller;

import com.ysg.servicetemplate.api.mapper.TemplateItemRequestMapper;
import com.ysg.servicetemplate.api.request.CreateTemplateItemRequest;
import com.ysg.servicetemplate.api.response.CreateTempalteItemResponse;
import com.ysg.servicetemplate.application.TemplateItemUseCaseApplication;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/template")
@Api(tags = "template api")
public class TemplateController {
    private final TemplateItemUseCaseApplication templateItemUseCaseApplication;

    private final TemplateItemRequestMapper templateRequestMapper = TemplateItemRequestMapper.INSTANCE;

    @PostMapping("/items")
    @ApiOperation("template item api")
    public CreateTempalteItemResponse createItem(@RequestBody CreateTemplateItemRequest templateCreateRequest) {
        return new CreateTempalteItemResponse(templateItemUseCaseApplication.create(templateRequestMapper.to(templateCreateRequest)));
    }

}
