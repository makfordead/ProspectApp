package com.macheal.app.prospect.feature.prospect.service;

import com.macheal.app.prospect.web.prospect.dto.ProspectRequest;
import com.macheal.app.prospect.web.prospect.dto.ProspectResponse;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface ProspectService {

    ResponseEntity<ProspectResponse> createProspect(final ProspectRequest prospectRequest,
                                                    final Principal principal);

    ResponseEntity<Page<ProspectResponse>> getProspects(Predicate predicate, final Pageable pageable
            , Principal principal);

    ResponseEntity<ProspectResponse> getProspectById(Principal principal, String prospectId);

    ResponseEntity<ProspectResponse> updateProspectById(Principal principal, String prospectId
            , ProspectRequest prospectRequest);
}
