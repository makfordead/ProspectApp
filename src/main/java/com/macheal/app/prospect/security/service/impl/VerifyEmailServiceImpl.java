package com.macheal.app.prospect.security.service.impl;

import com.macheal.app.prospect.exception.ServiceException;
import com.macheal.app.prospect.exception.constant.ErrorCodeEnum;
import com.macheal.app.prospect.security.repository.EmailVerifyRepository;
import com.macheal.app.prospect.security.repository.UserRepository;
import com.macheal.app.prospect.security.repository.entity.EmailVerify;
import com.macheal.app.prospect.security.repository.entity.QEmailVerify;
import com.macheal.app.prospect.security.repository.entity.QUser;
import com.macheal.app.prospect.security.repository.entity.User;
import com.macheal.app.prospect.security.service.VerifyEmailService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerifyEmailServiceImpl implements VerifyEmailService {

    @Autowired
    EmailVerifyRepository emailVerifyRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void verifyEmail(String token) {
        EmailVerify emailVerify = emailVerifyRepository.findOne(QEmailVerify.emailVerify
                .token.eq(token)).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND,
                "Invalid Token"));
        final User user = userRepository.findOne(
                QUser.user.id.eq(emailVerify.getUserId())
        ).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "User not found"));

        user.setEnabled(true);

        userRepository.save(user);


        emailVerifyRepository.deleteById(emailVerify.getId());

    }
}
