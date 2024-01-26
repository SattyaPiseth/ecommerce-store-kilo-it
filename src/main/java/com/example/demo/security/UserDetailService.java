package com.example.demo.security;

import com.example.demo.constant.MessageConstant;
import com.example.demo.db.entity.UserEntity;
import com.example.demo.db.reposiroty.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        UserEntity user = userRepository.findByUsernameFetchRolePermission(username);
        if (user == null)
            throw new UsernameNotFoundException(MessageConstant.AUTH.INCORRECT_USERNAME_OR_PASSWORD);

        return UserPrincipal.build(user);
    }

}
