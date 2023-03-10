package com.ysg.servicetemplate.domain.model;

import com.ysg.servicetemplate.domain.core.AggregateRoot;
import com.ysg.servicetemplate.domain.command.CreateItemCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateItem implements AggregateRoot {
    private String id;

    private String tempKey;

    private String tempValue;

    public static TemplateItem create(CreateItemCommand command) {
        return TemplateItem.builder().tempKey(command.getTempKey()).tempValue(command.getTempValue()).build();
    }
}
