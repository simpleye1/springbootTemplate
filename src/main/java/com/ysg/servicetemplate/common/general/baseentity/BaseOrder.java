package com.ysg.servicetemplate.common.general.baseentity;

public class BaseOrder {
    private final String orderField;
    private final OrderEnum order;

    public BaseOrder(String orderField, OrderEnum order) {
        this.orderField = orderField;
        this.order = order;
    }

    public String getOrderField() {
        return orderField;
    }

    public OrderEnum getOrder() {
        return order;
    }
}
