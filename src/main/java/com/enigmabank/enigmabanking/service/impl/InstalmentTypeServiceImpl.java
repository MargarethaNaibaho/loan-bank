package com.enigmabank.enigmabanking.service.impl;

import com.enigmabank.enigmabanking.constant.EInstalmentType;
import com.enigmabank.enigmabanking.dto.request.InstalmentTypeRequest;
import com.enigmabank.enigmabanking.entity.InstalmentType;
import com.enigmabank.enigmabanking.repository.InstalmentTypeRepository;
import com.enigmabank.enigmabanking.service.InstalmentTypeService;
import com.enigmabank.enigmabanking.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstalmentTypeServiceImpl implements InstalmentTypeService {
    private final InstalmentTypeRepository instalmentTypeRepository;
    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public InstalmentType createInstalmentType(InstalmentTypeRequest instalmentTypeRequest) {

        EInstalmentType instalmentType = instalmentTypeRequest.getInstalmentType();

        if(instalmentType == null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid instalment type");
        }

        Optional<InstalmentType> optionalRInstalmentType = instalmentTypeRepository.findByInstalmentType(instalmentType);
        if(!optionalRInstalmentType.isEmpty()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Instalment type already exists!");
        }

        InstalmentType newInstalmentType = InstalmentType.builder()
                .instalmentType(instalmentType)
                .build();

        return instalmentTypeRepository.saveAndFlush(newInstalmentType);
    }

    @Transactional(readOnly = true)
    @Override
    public InstalmentType getInstalmentTypeById(String id) {
        return instalmentTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instalment type isn't found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<InstalmentType> getAllInstalmentTypes() {
        List<InstalmentType> instalmentTypes = instalmentTypeRepository.findAll();
        if(instalmentTypes.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no instalment types in database");
        }

        return instalmentTypes;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public InstalmentType updateInstalmentType(InstalmentType instalmentType) {
        validationUtil.validate(instalmentType);
        Optional<InstalmentType> optionalRInstalmentType = instalmentTypeRepository.findByInstalmentType(instalmentType.getInstalmentType());
        if(!optionalRInstalmentType.isEmpty()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Instalment type already exists!");
        }

        return instalmentTypeRepository.saveAndFlush(instalmentType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteInstalmentType(String id) {
        Optional<InstalmentType> optionalRInstalmentType = instalmentTypeRepository.findById(id);
        if(optionalRInstalmentType.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Instalment type isn't found");
        }

        instalmentTypeRepository.deleteById(id);
    }
}
