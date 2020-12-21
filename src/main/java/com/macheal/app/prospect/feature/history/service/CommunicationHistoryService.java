package com.macheal.app.prospect.feature.history.service;

import com.macheal.app.prospect.web.communication.history.dto.CommunicationHistoryRequest;
import com.macheal.app.prospect.web.communication.history.dto.CommunicationHistoryResponse;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface CommunicationHistoryService {

    ResponseEntity<CommunicationHistoryResponse> createCommunicationHistory(
            String prospectId, Principal principal, CommunicationHistoryRequest communicationHistoryRequest
    );

    ResponseEntity<Page<CommunicationHistoryResponse>> getAllHistoryByProspectId(String prospectId, Predicate predicate, final Pageable pageable
            , Principal principal);
}
