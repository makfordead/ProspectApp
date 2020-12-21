package com.macheal.app.prospect.security.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Setter
@Getter
public class EmailVerify {
    @Id
    private String id = UUID.randomUUID().toString();

    String userId;

    String token;

}
