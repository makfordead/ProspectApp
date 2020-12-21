package com.macheal.app.prospect.feature.history.respository.entity;

import com.macheal.app.prospect.feature.prospect.repository.entity.Prospect;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommunicationHistory {
    @Id
    String communicationId = UUID.randomUUID().toString();

    LocalDate contactDate;

    String information;

    @OneToOne
    Prospect prospect;

}
