package com.ran.springbootShiro001.service;

import com.ran.springbootShiro001.bean.User;

public interface UserService {
    User findByUsername(String username);
}
