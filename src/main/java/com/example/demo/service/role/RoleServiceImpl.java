package com.example.demo.service.role;

import com.example.demo.db.entity.RoleEntity;
import com.example.demo.db.reposiroty.RoleRepository;
import com.example.demo.model.request.role.RoleRQ;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


/**
 * @author Sattya
 * create at 1/29/2024 3:37 PM
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public void createRole(RoleRQ roleRQ) {
        // Check for existing role name
        if (roleRepository.existsByNameAndCodeAllIgnoreCase(roleRQ.getName(),roleRQ.getCode())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"role existed");
        }

        // Create and save the role
//    RoleEntity role = RoleEntity.builder()
//                .name(roleRQ.getName())
//                .code(roleRQ.getCode())
//                .build();

        RoleEntity role = new RoleEntity();
        role.setName(roleRQ.getName());
        role.setCode(roleRQ.getCode());
        roleRepository.save(role);
    }
}
