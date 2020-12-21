package com.macheal.app.prospect.security.service;


import com.macheal.app.prospect.security.dto.SignUpRequest;

public interface UserService {

    void save(final SignUpRequest request);
}
