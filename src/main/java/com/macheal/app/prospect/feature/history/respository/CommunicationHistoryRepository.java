package com.macheal.app.prospect.feature.history.respository;

import com.macheal.app.prospect.feature.history.respository.entity.CommunicationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CommunicationHistoryRepository extends JpaRepository<CommunicationHistory, String>,
        QuerydslPredicateExecutor<CommunicationHistory> {
}
