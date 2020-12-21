package com.macheal.app.prospect.security.service.impl;


import com.macheal.app.prospect.security.repository.UserRepository;
import com.macheal.app.prospect.security.repository.entity.QUser;
import com.macheal.app.prospect.security.repository.entity.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Override
    public User loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findOne(QUser.user.username.eq(username))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }
}
