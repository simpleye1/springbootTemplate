package com.ysg.servicetemplate.builder;

import com.ysg.servicetemplate.infrastructure.repository.TemplateItemJpaRepository;
import com.ysg.servicetemplate.infrastructure.po.TemplateItemPO;
import com.ysg.servicetemplate.common.general.utils.SpringApplicationContext;

public class TemplateItemPOTestBuilder {
    private final TemplateItemPO entity = new TemplateItemPO();

    public TemplateItemPOTestBuilder withDefault() {
        return new TemplateItemPOTestBuilder().withKey("tempKey").withValue("value");
    }

    public TemplateItemPOTestBuilder withKey(String tempKey) {
        entity.setTempKey(tempKey);
        return this;
    }

    public TemplateItemPOTestBuilder withValue(String value) {
        entity.setTempValue(value);
        return this;
    }

    public TemplateItemPO persist() {
        return SpringApplicationContext.getBean(TemplateItemJpaRepository.class).saveAndFlush(entity);
    }


}
