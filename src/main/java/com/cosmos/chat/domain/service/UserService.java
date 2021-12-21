package com.cosmos.chat.domain.service;

import javax.servlet.http.HttpServletRequest;

import com.cosmos.chat.domain.model.Role;
import com.cosmos.chat.domain.model.User;

public interface UserService {
    User createUser(User user);

    Role createRole(Role r1);

    String login(String username, String password, HttpServletRequest req);

    String deleteUser(String username, HttpServletRequest request);

    String invalidate(String username, HttpServletRequest request);
}
