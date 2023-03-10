package com.ysg.servicetemplate.common.general.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationContext implements ApplicationContextAware {
    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(final ApplicationContext context)
            throws BeansException {
        CONTEXT = context;
    }

    public static ApplicationContext getContext() {
        return CONTEXT;
    }

    public static <T> T getBean(Class<T> clazz) {
        return CONTEXT.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return CONTEXT.getBean(name, clazz);
    }
}
