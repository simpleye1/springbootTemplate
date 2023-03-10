package com.ysg.servicetemplate.infrastructure.repository;

import com.ysg.servicetemplate.infrastructure.mapper.ItemPOMapper;
import com.ysg.servicetemplate.common.general.utils.UUIDUtil;
import com.ysg.servicetemplate.domain.model.TemplateItem;
import com.ysg.servicetemplate.domain.repository.TemplateItemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TemplateItemRepositoryImpl implements TemplateItemRepository {
    private final TemplateItemJpaRepository templateJpaRepository;

    private final ItemPOMapper itemPOMapper = ItemPOMapper.INSTANCE;

    @Override
    public TemplateItem save(TemplateItem templateItem) {
        if (StringUtils.isBlank(templateItem.getId())) {
            templateItem.setId(UUIDUtil.generateUUID());
        }
        return itemPOMapper.to(templateJpaRepository.save(itemPOMapper.to(templateItem)));
    }

}
