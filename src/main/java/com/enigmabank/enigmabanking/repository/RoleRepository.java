package com.enigmabank.enigmabanking.repository;

import com.enigmabank.enigmabanking.constant.ERole;
import com.enigmabank.enigmabanking.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(ERole name);
}
