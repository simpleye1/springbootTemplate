package com.ysg.servicetemplate.common.general.baseentity;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Sort;

import java.util.List;

public abstract class BaseRepositoryImpl {

    protected Sort getSort(BaseQuery query) {
        List<Sort.Order> orders = Lists.newLinkedList();
        query.getOrders().forEach(order -> {
            if (OrderEnum.ASC.equals(order.getOrder())) {
                orders.add(Sort.Order.asc(order.getOrderField()));
            }
            if (OrderEnum.DESC.equals(order.getOrder())) {
                orders.add(Sort.Order.desc(order.getOrderField()));
            }
        });

        if (CollectionUtils.isEmpty(orders)) {
            orders.add(Sort.Order.asc("id"));
        }
        return Sort.by(orders);
    }
}
