package com.enigmabank.enigmabanking.repository;

import com.enigmabank.enigmabanking.constant.EInstalmentType;
import com.enigmabank.enigmabanking.entity.InstalmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstalmentTypeRepository extends JpaRepository<InstalmentType, String> {
    Optional<InstalmentType> findByInstalmentType(EInstalmentType name);
}
