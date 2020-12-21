package com.macheal.app.prospect.feature.prospect.service.impl;

import com.macheal.app.prospect.exception.ServiceException;
import com.macheal.app.prospect.exception.constant.ErrorCodeEnum;
import com.macheal.app.prospect.feature.prospect.repository.ProspectRepository;
import com.macheal.app.prospect.feature.prospect.repository.entity.Prospect;
import com.macheal.app.prospect.feature.prospect.repository.entity.QProspect;
import com.macheal.app.prospect.feature.prospect.service.ProspectService;
import com.macheal.app.prospect.security.repository.UserRepository;
import com.macheal.app.prospect.security.repository.entity.QUser;
import com.macheal.app.prospect.security.repository.entity.User;
import com.macheal.app.prospect.web.prospect.dto.ProspectRequest;
import com.macheal.app.prospect.web.prospect.dto.ProspectResponse;
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
public class ProspectServiceImpl implements ProspectService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProspectRepository prospectRepository;

    @Override
    public ResponseEntity<ProspectResponse> createProspect(ProspectRequest prospectRequest, Principal principal) {
        Prospect prospect = modelMapper.map(prospectRequest, Prospect.class);
        prospect.setProspectId(UUID.randomUUID().toString());

        final User user = userRepository.findOne(QUser.user.username.eq(principal.getName()))
                .orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND,
                        "User not found"));

        prospect.setUser(user);
        prospect.setProspectCreated(LocalDate.now());
        prospect.setEmailReminder(true);
        prospect.setFrequencyOfContact(7);
        prospect.setNextReminder(LocalDate.now().plusDays(7));
        prospect.setProspectCreated(LocalDate.now());
        prospect.setProspectSince(LocalDate.now());
        prospect = prospectRepository.save(prospect);


        final ProspectResponse prospectResponse = modelMapper.map(prospect, ProspectResponse.class);

        return new ResponseEntity<>(prospectResponse, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Page<ProspectResponse>> getProspects(Predicate predicate, final Pageable pageable
            , Principal principal) {
        predicate = ExpressionUtils.allOf(QProspect.prospect.user
                .username.eq(principal.getName()));
        return new ResponseEntity<>(prospectRepository.findAll(predicate, pageable).map(this::build)
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProspectResponse> getProspectById(Principal principal, String prospectId) {
        final Prospect prospect = prospectRepository.findOne(
                QProspect.prospect.prospectId.eq(prospectId).and(
                        QProspect.prospect.user.username.eq(principal.getName())
                )).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND,
                "Either the prospect does not belong to given user or invalid prospect ID"));

        return new ResponseEntity<>(modelMapper.map(prospect, ProspectResponse.class),
                HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ProspectResponse> updateProspectById(Principal principal, String prospectId, ProspectRequest prospectRequest) {
        Prospect prospect = prospectRepository.findOne(QProspect.prospect
                .user.username.eq(principal.getName()).and(QProspect.prospect.prospectId.eq(prospectId)))
                .orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "Invalid prospect Id or the prospect doesnt" +
                        "belong"));
        Prospect updatedProspect = modelMapper.map(prospectRequest, Prospect.class);
        updatedProspect.setUser(prospect.getUser());
        updatedProspect.setProspectId(prospect.getProspectId());
        updatedProspect.setProspectCreated(prospect.getProspectCreated());
        updatedProspect.setLastContactOn(prospect.getLastContactOn());
        updatedProspect = prospectRepository.save(updatedProspect);

        return new ResponseEntity(modelMapper.map(updatedProspect, Prospect.class), HttpStatus.OK);
    }

    private ProspectResponse build(final Prospect prospect) {
        return modelMapper.map(prospect, ProspectResponse.class);
    }

}
