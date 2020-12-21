package com.macheal.app.prospect.web.prospect.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProspectResponse {
    String prospectId = UUID.randomUUID().toString();

    String prospectName;
    String communicationForm;
    //@JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate prospectSince;
    String prospectType;
    String email;
    String address;
    String phone;
    String facebook;
    String linkedIn;
    String instagram;
    Boolean emailReminder;
    Integer frequencyOfContact;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate lastContactOn;
    Integer importance;
    String additionalInformation;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate prospectCreated;

}
