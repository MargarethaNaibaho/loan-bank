package com.enigmabank.enigmabanking.service;

import com.enigmabank.enigmabanking.dto.request.InstalmentTypeRequest;
import com.enigmabank.enigmabanking.entity.InstalmentType;

import java.util.List;

public interface InstalmentTypeService {
    InstalmentType createInstalmentType(InstalmentTypeRequest instalmentTypeRequest);
    List<InstalmentType> getAllInstalmentTypes();
    InstalmentType getInstalmentTypeById(String id);
    InstalmentType updateInstalmentType(InstalmentType instalmentType);
    void deleteInstalmentType(String id);
}
