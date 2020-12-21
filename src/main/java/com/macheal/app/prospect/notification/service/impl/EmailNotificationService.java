package com.macheal.app.prospect.notification.service.impl;

import com.macheal.app.prospect.notification.dto.EmailDataRequest;
import com.macheal.app.prospect.notification.dto.NotificationType;
import com.macheal.app.prospect.notification.service.NotificationService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmailNotificationService implements NotificationService<EmailDataRequest> {

    JavaMailSender mailSender;

    Configuration freemarkerConfig;

    @Override
    @SneakyThrows
    public void prepareAndSendMessage(final EmailDataRequest data, final NotificationType notificationType, final Locale locale, final String template) {
        log.info("Start Sending email to: {} with content: {}", data.getTo(), data.getContent());
        final MimeMessage message = mailSender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
        helper.setFrom(data.getFrom());
        helper.setTo(data.getTo().toArray(new String[0]));
        helper.setSubject(data.getSubject());
        final Template t = freemarkerConfig.getTemplate("/" + notificationType.name().toLowerCase() + "/" + locale.toString() + template);
        helper.setText(FreeMarkerTemplateUtils.processTemplateIntoString(t, data.getContent()), true);
        emptyIfNull(data.getAttachmentPaths()).forEach(filePath -> Try.run(() -> attacheFiles(helper, filePath)));
        mailSender.send(message);
        log.info("End Sending email to: {} with content: {}", data.getTo(), data.getContent());
    }

    @SneakyThrows
    private void attacheFiles(final MimeMessageHelper helper, final String filePath) {
        final File file = new ClassPathResource(filePath).getFile();
        final FileSystemResource fsr = new FileSystemResource(file);
        helper.addAttachment(file.getName(), fsr);
    }
}
