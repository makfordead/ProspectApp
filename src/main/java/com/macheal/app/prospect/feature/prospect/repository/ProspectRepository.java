package com.macheal.app.prospect.feature.prospect.repository;

import com.macheal.app.prospect.feature.prospect.repository.entity.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProspectRepository extends JpaRepository<Prospect,
        String>, QuerydslPredicateExecutor<Prospect> {
}
