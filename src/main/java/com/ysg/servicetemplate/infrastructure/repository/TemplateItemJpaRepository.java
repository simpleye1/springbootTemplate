package com.ysg.servicetemplate.infrastructure.repository;

import com.ysg.servicetemplate.infrastructure.po.TemplateItemPO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateItemJpaRepository extends JpaRepository<TemplateItemPO, String> {
}
