package com.macheal.app.prospect.web.prospect.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProspectRequest {
    String prospectName;
    String communicationForm;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
    Integer importance;
    String additionalInformation;
}
