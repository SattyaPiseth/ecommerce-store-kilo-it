package com.example.demo.controller;

import com.example.demo.base.BaseController;
import com.example.demo.base.StructureRS;
import com.example.demo.model.request.auth.LoginRQ;
import com.example.demo.model.request.auth.RegisterRQ;
import com.example.demo.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseEntity<StructureRS> register(@Validated @RequestBody RegisterRQ registerRQ){
        return response(authService.register(registerRQ));
    }
}



