package com.macheal.app.prospect.web.communication.history.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommunicationHistoryResponse {

    String prospectId = UUID.randomUUID().toString();

    LocalDate contactDate;

    String information;
}
