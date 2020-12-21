package com.macheal.app.prospect.security.service.impl;


import com.macheal.app.prospect.notification.dto.EmailDataRequest;
import com.macheal.app.prospect.notification.dto.NotificationType;
import com.macheal.app.prospect.notification.service.NotificationService;
import com.macheal.app.prospect.security.dto.SignUpRequest;
import com.macheal.app.prospect.security.repository.EmailVerifyRepository;
import com.macheal.app.prospect.security.repository.RoleRepository;
import com.macheal.app.prospect.security.repository.UserRepository;
import com.macheal.app.prospect.security.repository.entity.EmailVerify;
import com.macheal.app.prospect.security.repository.entity.QRole;
import com.macheal.app.prospect.security.repository.entity.User;
import com.macheal.app.prospect.security.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    @Value("${notification-redirect-url.user-confirm-register-redirect}")
    String userConfirmationRegisterRedirectUrl;

    @Autowired
    NotificationService<EmailDataRequest> notificationService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailVerifyRepository emailVerifyRepository;

    public UserServiceImpl() {
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void save(final SignUpRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setEnabled(false);


        if (CollectionUtils.isNotEmpty(request.getRoles())) {
            user.setRoles(IterableUtils.toList(roleRepository.findAll(QRole.role.id.in(request.getRoles()))));
        }
        user = userRepository.save(user);

        EmailVerify emailVerify = new EmailVerify();

        emailVerify.setUserId(user.getId());

        emailVerify.setToken(UUID.randomUUID().toString());

        emailVerify = emailVerifyRepository.save(emailVerify);

        final EmailDataRequest r = EmailDataRequest.builder()
                .from("noreply@gmail.com")
                .to(Collections.singletonList("kodwanikapil@gmail.com"))
                .subject("Email Verification")
                .content(Map.of("user", user,
                        "verify", emailVerify,
                        "redirectUrl", userConfirmationRegisterRedirectUrl)).build();
        notificationService.prepareAndSendMessage(r, NotificationType.EMAIL,
                Locale.forLanguageTag("en"), "/user-confirm-register.ftl");
    }
}
