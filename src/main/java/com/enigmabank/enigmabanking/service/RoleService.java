package com.enigmabank.enigmabanking.service;

import com.enigmabank.enigmabanking.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role getOrSave(Role role);
}
