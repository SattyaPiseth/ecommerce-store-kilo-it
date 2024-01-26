package com.example.demo.service;

import com.example.demo.base.BaseService;
import com.example.demo.base.StructureRS;
import com.example.demo.constant.MessageConstant;
import com.example.demo.exception.httpstatus.BadRequestException;
import com.example.demo.model.request.auth.LoginRQ;
import com.example.demo.security.UserPrincipal;
import com.example.demo.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sombath
 * create at 26/1/24 2:08 AM
 */

@Service
@RequiredArgsConstructor
public class AuthService extends BaseService {

    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    private final PasswordEncoder passwordEncoder;

    public StructureRS login(LoginRQ request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication auth = authenticationManager.authenticate(authenticationToken);

        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();

        if (!userPrincipal.getStatus())
            throw new BadRequestException(MessageConstant.AUTH.ACCOUNT_DEACTIVATE);

        Map<String, Object> respond = new HashMap<>();

        respond.put("user", userPrincipal);
        respond.put("token", tokenUtils.generateToken(userPrincipal));

        return response(respond);
    }

}
