package com.ysg.servicetemplate.common.general.exceptionhandling.responsehandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorKeyValue<K, V> {

    private K field;

    private V value;
}
