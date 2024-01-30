package com.example.demo.service.user;

import com.example.demo.model.request.auth.RegisterRQ;

import javax.management.relation.RoleNotFoundException;

/**
 * @author Sattya
 * create at 1/29/2024 10:18 PM
 */
public interface UserService {
    /**
     * Create new user
     * @param registerRQ of Request data from client
     */
    void createNewUser(RegisterRQ registerRQ) throws RoleNotFoundException;
}
