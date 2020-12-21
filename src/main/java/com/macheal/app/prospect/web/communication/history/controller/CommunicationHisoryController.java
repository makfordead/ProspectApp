package com.macheal.app.prospect.web.communication.history.controller;

import com.macheal.app.prospect.feature.history.service.CommunicationHistoryService;
import com.macheal.app.prospect.feature.prospect.repository.entity.Prospect;
import com.macheal.app.prospect.web.communication.history.dto.CommunicationHistoryRequest;
import com.macheal.app.prospect.web.communication.history.dto.CommunicationHistoryResponse;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("communication-history")
@FieldDefaults(level = AccessLevel.PRIVATE)
@CrossOrigin
public class CommunicationHisoryController {
    @Autowired
    CommunicationHistoryService communicationHistoryService;

    @PostMapping("/create/{prospectId}")
    public ResponseEntity<CommunicationHistoryResponse> createCommunicationHistory(
            @PathVariable("prospectId") String prospectId,
            Principal principal,
            @RequestBody CommunicationHistoryRequest communicationHistoryRequest
    ) {
        return communicationHistoryService.createCommunicationHistory(prospectId,
                principal, communicationHistoryRequest);

    }

    @GetMapping("/get/{prospectId}")
    public ResponseEntity<Page<CommunicationHistoryResponse>> getAllCommunicationHistoryById(
            @QuerydslPredicate(root = Prospect.class) Predicate predicate,
            Pageable pageable,
            final Principal principal, @PathVariable("prospectId")
                    String prospectId
    ) {
        return communicationHistoryService.getAllHistoryByProspectId(prospectId, predicate, pageable,
                principal);
    }
}
