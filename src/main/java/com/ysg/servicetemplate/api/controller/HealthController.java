package com.ysg.servicetemplate.api.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "health api")
public class HealthController {
    @GetMapping("/health")
    public String health() {
        return "health";
    }

}
