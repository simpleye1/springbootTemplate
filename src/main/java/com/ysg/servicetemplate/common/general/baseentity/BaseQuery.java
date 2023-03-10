package com.ysg.servicetemplate.common.general.baseentity;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseQuery {
    private int page;

    private int size;

    private final List<BaseOrder> orders = new LinkedList<>();

    public BaseQuery asc(String orderField) {
        assembleOrder(new BaseOrder(orderField, OrderEnum.ASC));
        return this;
    }

    public BaseQuery desc(String orderField) {
        assembleOrder(new BaseOrder(orderField, OrderEnum.DESC));
        return this;
    }


    public List<BaseOrder> getOrders() {
        return orders;
    }

    protected void assembleOrder(BaseOrder baseOrder) {
        orders.add(baseOrder);
    }

    public void assemblePage(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getOffset() {
        return (long) (page - 1) * (long) size;
    }
}
