package com.macheal.app.prospect.feature.event.repository;

import com.macheal.app.prospect.feature.event.repository.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
}
