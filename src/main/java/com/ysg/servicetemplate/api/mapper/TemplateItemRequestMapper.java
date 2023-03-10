package com.ysg.servicetemplate.api.mapper;

import com.ysg.servicetemplate.api.request.CreateTemplateItemRequest;
import com.ysg.servicetemplate.application.dto.CreateItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TemplateItemRequestMapper {
    TemplateItemRequestMapper INSTANCE = Mappers.getMapper(TemplateItemRequestMapper.class);

    CreateItemDto to(CreateTemplateItemRequest templateCreateRequest);
}
