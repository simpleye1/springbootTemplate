package com.ysg.servicetemplate.api.outbound.persistence.template;

import com.ysg.servicetemplate.common.general.utils.UUIDUtil;
import com.ysg.servicetemplate.domain.model.TemplateItem;
import com.ysg.servicetemplate.infrastructure.repository.TemplateItemJpaRepository;
import com.ysg.servicetemplate.infrastructure.repository.TemplateItemRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class TemplateItemRepositoryImplTest {
    @Mock
    private TemplateItemJpaRepository templateItemJpaRepository;

    @InjectMocks
    private TemplateItemRepositoryImpl templateItemRepositoryImpl;

    @Test
    public void save_should_success_with_id() {
        TemplateItem templateItem = TemplateItem.builder().id(UUIDUtil.generateUUID()).tempKey("key").tempValue("value").build();

        templateItemRepositoryImpl.save(templateItem);

        verify(templateItemJpaRepository).save(argThat(item -> {
            assertThat(item.getId()).isEqualTo(templateItem.getId());
            assertThat(item.getTempKey()).isEqualTo(templateItem.getTempKey());
            assertThat(item.getTempValue()).isEqualTo(templateItem.getTempValue());
            return true;
        }));
    }

    @Test
    public void save_should_success_without_id() {
        TemplateItem templateItem = TemplateItem.builder().tempKey("key").tempValue("value").build();

        templateItemRepositoryImpl.save(templateItem);

        verify(templateItemJpaRepository).save(argThat(item -> {
            assertThat(item.getId()).isNotNull();
            assertThat(item.getTempKey()).isEqualTo(templateItem.getTempKey());
            assertThat(item.getTempValue()).isEqualTo(templateItem.getTempValue());
            return true;
        }));
    }

}