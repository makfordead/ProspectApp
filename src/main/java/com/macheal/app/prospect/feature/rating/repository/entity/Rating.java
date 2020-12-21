package com.macheal.app.prospect.feature.rating.repository.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Rating {

    @Id
    String ratingId = UUID.randomUUID().toString();

    Integer rating;
}
