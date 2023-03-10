package com.ysg.servicetemplate.infrastructure.mapper;

import com.ysg.servicetemplate.infrastructure.po.TemplateItemPO;
import com.ysg.servicetemplate.domain.model.TemplateItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemPOMapper {
    ItemPOMapper INSTANCE = Mappers.getMapper(ItemPOMapper.class);

    TemplateItemPO to(TemplateItem templateItem);

    TemplateItem to(TemplateItemPO itemPO);
}
