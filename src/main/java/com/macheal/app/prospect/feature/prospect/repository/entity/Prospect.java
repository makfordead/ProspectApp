package com.macheal.app.prospect.feature.prospect.repository.entity;

import com.macheal.app.prospect.security.repository.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Prospect {
    @Id
    String prospectId = UUID.randomUUID().toString();

    String prospectName;
    String communicationForm;
    LocalDate prospectSince;
    String prospectType;
    String email;
    String address;
    String phone;
    String facebook;
    String linkedIn;
    String instagram;
    boolean emailReminder;
    int frequencyOfContact;
    LocalDate lastContactOn;
    Integer importance;
    String additionalInformation;
    LocalDate prospectCreated;
    LocalDate nextReminder;

    @OneToOne
    User user;
}
