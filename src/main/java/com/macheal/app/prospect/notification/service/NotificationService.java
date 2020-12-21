package com.macheal.app.prospect.notification.service;


import com.macheal.app.prospect.notification.dto.NotificationType;

import java.util.Locale;

@FunctionalInterface
public interface NotificationService<T> {

    void prepareAndSendMessage(final T content, final NotificationType notificationType, final Locale locale, final String template);

}
