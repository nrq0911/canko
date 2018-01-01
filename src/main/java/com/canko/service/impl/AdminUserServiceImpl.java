package com.canko.service.impl;

import com.canko.domain.AdminUser;
import com.canko.mapper.AdminUserMapper;
import com.canko.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements AdminUserService{

    @Autowired
    private AdminUserMapper userMapper;

    @Override
    public AdminUser getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public void addAdminUser(AdminUser user) {
        userMapper.insertAdminUser(user);
    }

    @Override
    public void updateAdminUser(AdminUser user) {
        userMapper.updateAdminUserById(user);
    }
}
