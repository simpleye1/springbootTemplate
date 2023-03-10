package com.ysg.servicetemplate.common.general.event;

import org.springframework.context.ApplicationEvent;

public class BaseApplicationEvent<T> extends ApplicationEvent {
    public BaseApplicationEvent(T source) {
        super(source);
    }

    @Override
    public T getSource() {
        return (T) super.getSource();
    }
}

