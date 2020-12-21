package com.macheal.app.prospect.schedule;

import com.macheal.app.prospect.feature.prospect.repository.ProspectRepository;
import com.macheal.app.prospect.notification.dto.EmailDataRequest;
import com.macheal.app.prospect.notification.dto.NotificationType;
import com.macheal.app.prospect.notification.service.NotificationService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProspectEmailScheduler {

    @Autowired
    NotificationService<EmailDataRequest> notificationService;

    @Autowired
    ProspectRepository prospectRepository;
    //for One day
    @Scheduled(cron  = "0 15 10 * * ?")
    public void sendProspectEmails() {
        log.info("Starting to search for prospect to send email");
        prospectRepository.findAll().forEach(prospect -> {

            if (Objects.nonNull(prospect.getLastContactOn())  && prospect.isEmailReminder() && nonNull(prospect.getLastContactOn()) &&
                    prospect.getLastContactOn().plusDays(prospect.getFrequencyOfContact()).
                            compareTo(LocalDate.now()) == 0) {
                final EmailDataRequest r = EmailDataRequest.builder()
                        .from("noreply@gmail.com")
                        .to(Collections.singletonList(prospect.getUser().getUsername()))
                        .subject("Contact your prospect")
                        .content(Map.of("prospect",prospect)).build();
                notificationService.prepareAndSendMessage(r, NotificationType.EMAIL,
                        Locale.forLanguageTag("en"), "/user-prospectnotfication.ftl");
            }


        });

    }
}
