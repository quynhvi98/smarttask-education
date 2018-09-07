package com.fpt.services.role.impl;

import com.fpt.entity.Role;
import com.fpt.repositories.role.RoleRepository;
import com.fpt.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findById(Integer id) {
        return roleRepository.findOne(id);
    }
}
