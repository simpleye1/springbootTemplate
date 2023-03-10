package com.ysg.servicetemplate.common.general.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventPublisher implements ApplicationEventPublisherAware {

    private static ApplicationEventPublisher PUBLISHER;

    @Override
    public void setApplicationEventPublisher(final ApplicationEventPublisher applicationEventPublisher) {
        setPublisher(applicationEventPublisher);
    }

    private static void setPublisher(ApplicationEventPublisher applicationEventPublisher) {
        PUBLISHER = applicationEventPublisher;
    }

    public static void publish(ApplicationEvent event) {
        if (PUBLISHER != null) {
            PUBLISHER.publishEvent(event);
            log.info("publishEventName {}", event.getClass().getSimpleName());
        }
    }
}
