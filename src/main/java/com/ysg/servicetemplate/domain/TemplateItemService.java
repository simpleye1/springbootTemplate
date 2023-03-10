package com.ysg.servicetemplate.domain;

import com.ysg.servicetemplate.domain.core.DomainService;
import com.ysg.servicetemplate.domain.command.CreateItemCommand;
import com.ysg.servicetemplate.domain.model.TemplateItem;
import com.ysg.servicetemplate.domain.repository.TemplateItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateItemService implements DomainService {
    private final TemplateItemRepository templateItemRepository;

    public TemplateItem createItem(CreateItemCommand command) {
        return templateItemRepository.save(TemplateItem.create(command));
    }
}
