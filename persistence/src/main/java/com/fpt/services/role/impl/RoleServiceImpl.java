package com.fpt.services.role.impl;

import com.fpt.entity.Role;
import com.fpt.repositories.role.RoleDao;
import com.fpt.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Override
    public Role findById(String id) {
        return roleDao.findOne(id);
    }
}
