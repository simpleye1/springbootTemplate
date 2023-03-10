package com.ysg.servicetemplate.infrastructure.po;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "temp_item")
public class TemplateItemPO {

    @Id
    private String id;

    private String tempKey;

    private String tempValue;
}
