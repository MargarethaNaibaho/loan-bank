package com.enigmabank.enigmabanking.service.impl;

import com.enigmabank.enigmabanking.entity.Role;
import com.enigmabank.enigmabanking.repository.RoleRepository;
import com.enigmabank.enigmabanking.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(Role role) {
        Optional<Role> optionalRole = roleRepository.findByRole(role.getRole());
        if(!optionalRole.isEmpty()){
            return optionalRole.get();
        }

        return roleRepository.saveAndFlush(role);
    }
}
