package com.macheal.app.prospect.security.controller;

import com.macheal.app.prospect.security.service.VerifyEmailService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verify-email")
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerifyEmailController {

    @Autowired
    VerifyEmailService verifyEmailService;

    @PostMapping
    public ResponseEntity verifyEmail(@RequestParam("token") String token) {
        verifyEmailService.verifyEmail(token);
        return new ResponseEntity(HttpStatus.OK);
    }
}
