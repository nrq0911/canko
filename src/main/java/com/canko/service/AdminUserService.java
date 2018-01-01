package com.canko.service;

import com.canko.domain.AdminUser;

public interface AdminUserService {

    AdminUser getUserByUsername(String username);

    void addAdminUser(AdminUser user);

    void updateAdminUser(AdminUser user);

}
