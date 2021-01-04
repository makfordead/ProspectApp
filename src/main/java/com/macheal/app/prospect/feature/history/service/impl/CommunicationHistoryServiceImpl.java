package com.macheal.app.prospect.feature.history.service.impl;

import com.macheal.app.prospect.exception.ServiceException;
import com.macheal.app.prospect.exception.constant.ErrorCodeEnum;
import com.macheal.app.prospect.feature.history.respository.CommunicationHistoryRepository;
import com.macheal.app.prospect.feature.history.respository.entity.CommunicationHistory;
import com.macheal.app.prospect.feature.history.respository.entity.QCommunicationHistory;
import com.macheal.app.prospect.feature.history.service.CommunicationHistoryService;
import com.macheal.app.prospect.feature.prospect.repository.ProspectRepository;
import com.macheal.app.prospect.feature.prospect.repository.entity.Prospect;
import com.macheal.app.prospect.feature.prospect.repository.entity.QProspect;
import com.macheal.app.prospect.web.communication.history.dto.CommunicationHistoryRequest;
import com.macheal.app.prospect.web.communication.history.dto.CommunicationHistoryResponse;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommunicationHistoryServiceImpl implements CommunicationHistoryService {

    @Autowired
    CommunicationHistoryRepository communicationHistoryRepository;

    @Autowired
    ProspectRepository prospectRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<CommunicationHistoryResponse> createCommunicationHistory(String prospectId, Principal principal
            , CommunicationHistoryRequest communicationHistoryRequest) {
        final Prospect prospect = prospectRepository.findOne(
                QProspect.prospect.prospectId.eq(prospectId)
                        .and(QProspect.prospect.user.username.eq(principal.getName()))

        ).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND,
                "Prospect id invalid or does not belong to given user"));


        CommunicationHistory communicationHistory = modelMapper.map(
                communicationHistoryRequest, CommunicationHistory.class

        );
        communicationHistory.setContactDate(LocalDate.now());
        communicationHistory.setCommunicationId(UUID.randomUUID().toString());
        prospect.setLastContactOn(LocalDate.now());
        prospectRepository.save(prospect);
        communicationHistory.setProspect(prospect);

        communicationHistory = communicationHistoryRepository.save(communicationHistory);

        return new ResponseEntity<>(modelMapper.map(communicationHistory
                , CommunicationHistoryResponse.class),
                HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Page<CommunicationHistoryResponse>> getAllHistoryByProspectId(String prospectId, Predicate predicate, final Pageable pageable
            , Principal principal) {
        predicate = ExpressionUtils.allOf(QCommunicationHistory.communicationHistory.prospect.prospectId
                .eq(prospectId));
        return new ResponseEntity(communicationHistoryRepository.findAll(predicate, pageable).map(this::build)
                , HttpStatus.OK);
    }

    private CommunicationHistoryResponse build(final CommunicationHistory communicationHistory) {
        return modelMapper.map(communicationHistory, CommunicationHistoryResponse.class);
    }
}
