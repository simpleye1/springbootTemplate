package com.ysg.servicetemplate.application;

import com.ysg.servicetemplate.application.mapper.CreateItemCommandMapper;
import com.ysg.servicetemplate.application.dto.CreateItemDto;
import com.ysg.servicetemplate.domain.TemplateItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TemplateItemUseCaseApplication {
    private final TemplateItemService templateService;

    private final CreateItemCommandMapper createItemCommandMapper = CreateItemCommandMapper.INSTANCE;

    public String create(CreateItemDto templateCreateDto) {
        return templateService.createItem(createItemCommandMapper.to(templateCreateDto)).getId();
    }
}
