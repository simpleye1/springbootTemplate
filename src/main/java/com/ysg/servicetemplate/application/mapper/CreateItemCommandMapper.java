package com.ysg.servicetemplate.application.mapper;

import com.ysg.servicetemplate.application.dto.CreateItemDto;
import com.ysg.servicetemplate.domain.command.CreateItemCommand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreateItemCommandMapper {
    CreateItemCommandMapper INSTANCE = Mappers.getMapper(CreateItemCommandMapper.class);

    CreateItemCommand to(CreateItemDto templateCreateDto);
}
