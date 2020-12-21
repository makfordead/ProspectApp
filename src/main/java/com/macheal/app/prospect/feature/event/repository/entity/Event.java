package com.macheal.app.prospect.feature.event.repository.entity;

import com.macheal.app.prospect.feature.constant.EventType;
import com.macheal.app.prospect.validator.annotation.Enum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event {
    @Id
    String eventId = UUID.randomUUID().toString();


    @Enum(enumClass = EventType.class)
    String eventType;

    LocalDateTime eventDate;

    String content;

}
