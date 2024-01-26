package com.example.demo.service.auth;

import com.example.demo.base.StructureRS;
import com.example.demo.model.request.auth.LoginRQ;

/**
 * @author Sattya
 * create at 1/26/2024 11:50 PM
 */
public interface AuthService {
    StructureRS login(LoginRQ request);
}
