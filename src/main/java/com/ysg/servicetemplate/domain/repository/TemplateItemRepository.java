package com.ysg.servicetemplate.domain.repository;

import com.ysg.servicetemplate.domain.core.Repository;
import com.ysg.servicetemplate.domain.model.TemplateItem;

public interface TemplateItemRepository extends Repository {
    TemplateItem save(TemplateItem templateItem);
}
