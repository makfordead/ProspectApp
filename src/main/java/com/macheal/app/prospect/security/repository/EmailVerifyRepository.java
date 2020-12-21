package com.macheal.app.prospect.security.repository;


import com.macheal.app.prospect.security.repository.entity.EmailVerify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface EmailVerifyRepository extends JpaRepository<EmailVerify, String>,
        QuerydslPredicateExecutor<EmailVerify> {
}
