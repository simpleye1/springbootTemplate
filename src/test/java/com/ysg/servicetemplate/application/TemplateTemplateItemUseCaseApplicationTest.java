package com.ysg.servicetemplate.application;

import com.ysg.servicetemplate.application.dto.CreateItemDto;
import com.ysg.servicetemplate.common.general.utils.UUIDUtil;
import com.ysg.servicetemplate.domain.TemplateItemService;
import com.ysg.servicetemplate.domain.command.CreateItemCommand;
import com.ysg.servicetemplate.domain.model.TemplateItem;
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
class TemplateTemplateItemUseCaseApplicationTest {
    @Mock
    private TemplateItemService templateService;

    @InjectMocks
    private TemplateItemUseCaseApplication templateItemUseCaseApplication;

    @Test
    public void create_should_success() {
        CreateItemDto createItemDto = CreateItemDto.builder().tempKey(UUIDUtil.generateUUID()).tempValue("value").build();
        TemplateItem mockReturn = TemplateItem.builder().id(UUIDUtil.generateUUID()).tempKey(createItemDto.getTempKey()).tempValue(createItemDto.getTempValue()).build();
        when(templateService.createItem(any())).thenReturn(mockReturn);

        String actual = templateItemUseCaseApplication.create(createItemDto);

        assertThat(actual).isEqualTo(mockReturn.getId());

        verify(templateService).createItem(CreateItemCommand.builder().tempKey(createItemDto.getTempKey()).tempValue(createItemDto.getTempValue()).build());
    }
}