package com.macheal.app.prospect.web.prospect.create.controller;


import com.macheal.app.prospect.feature.prospect.repository.entity.Prospect;
import com.macheal.app.prospect.feature.prospect.service.ProspectService;
import com.macheal.app.prospect.web.prospect.dto.ProspectRequest;
import com.macheal.app.prospect.web.prospect.dto.ProspectResponse;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/prospects")
@FieldDefaults(level = AccessLevel.PRIVATE)
@CrossOrigin
public class ProspectController {

    @Autowired
    ProspectService prospectService;

    @PostMapping("/create")
    public ResponseEntity<ProspectResponse> createProspect(@RequestBody ProspectRequest prospectRequest,
                                                           final Principal principal) {
        return prospectService.createProspect(prospectRequest, principal);
    }

    @GetMapping("/get")
    public ResponseEntity<Page<ProspectResponse>> getProduct(
            @QuerydslPredicate(root = Prospect.class) Predicate predicate,
            Pageable pageable,
            Principal principal
    ) {
        return prospectService.getProspects(predicate,
                pageable, principal);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ProspectResponse> getProspect(final Principal principal,
                                                        @PathVariable("id")
                                                                String prospectId) {
        return prospectService.getProspectById(principal, prospectId);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ProspectResponse> updateProspect(final Principal principal,
                                                           @PathVariable("id")
                                                                   String prospectId,
                                                           @RequestBody ProspectRequest
                                                                   prospectRequest) {
        return prospectService.updateProspectById(principal, prospectId, prospectRequest);
    }
}
