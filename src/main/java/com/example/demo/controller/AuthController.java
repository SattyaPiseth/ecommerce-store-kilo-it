package com.example.demo.controller;

import com.example.demo.base.BaseController;
import com.example.demo.base.StructureRS;
import com.example.demo.model.request.auth.LoginRQ;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sombath
 * create at 23/1/24 3:35 PM
 */

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<StructureRS> login(@Validated @RequestBody LoginRQ loginRQ) {
        return response(authService.login(loginRQ));
    }

}



