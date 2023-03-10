package com.ysg.servicetemplate.domain.template;

import com.ysg.servicetemplate.common.general.utils.UUIDUtil;
import com.ysg.servicetemplate.domain.TemplateItemService;
import com.ysg.servicetemplate.domain.command.CreateItemCommand;
import com.ysg.servicetemplate.domain.model.TemplateItem;
import com.ysg.servicetemplate.domain.repository.TemplateItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TemplateTemplateItemServiceTest {
    @Mock
    private TemplateItemRepository templateItemRepository;

    @InjectMocks
    private TemplateItemService templateItemService;

    @Test
    public void createItem_should_success() {
        CreateItemCommand command = CreateItemCommand.builder().tempKey(UUIDUtil.generateUUID()).tempValue("value").build();
        TemplateItem mockReturn = TemplateItem.builder().id(UUIDUtil.generateUUID()).tempKey(command.getTempKey()).tempValue(command.getTempValue()).build();
        when(templateItemRepository.save(any())).thenReturn(mockReturn);

        TemplateItem actual = templateItemService.createItem(command);

        assertThat(actual).usingRecursiveComparison().isEqualTo(mockReturn);

        verify(templateItemRepository).save(TemplateItem.builder().tempKey(command.getTempKey()).tempValue(command.getTempValue()).build());
    }

}