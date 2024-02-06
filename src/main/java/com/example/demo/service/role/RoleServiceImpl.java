package com.example.demo.service.role;

import com.example.demo.base.BaseListingRQ;
import com.example.demo.base.BaseService;
import com.example.demo.base.StructureRS;
import com.example.demo.constant.MessageConstant;
import com.example.demo.db.entity.RoleEntity;
import com.example.demo.db.repository.RoleRepository;
import com.example.demo.exception.httpstatus.BadRequestException;
import com.example.demo.mapper.RoleEntityMapper;
import com.example.demo.model.projection.RoleEntityInfo;
import com.example.demo.model.request.role.RoleRQ;
import com.example.demo.model.request.role.UpdateRoleRQ;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


/**
 * @author Sattya
 * create at 1/29/2024 3:37 PM
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl extends BaseService implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleMapper;
    @Transactional
    @Override
    public void createRole(RoleRQ roleRQ) {
        // Check for existing role name
        if (roleRepository.existsByNameAndCodeAllIgnoreCase(roleRQ.getName(),roleRQ.getCode())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"role existed");
        }
        RoleEntity role = new RoleEntity();
        role.setName(roleRQ.getName());
        role.setCode(roleRQ.getCode());
        roleRepository.save(role);
    }
    @Transactional
    @Override
    public void deleteRole(Long roleId) {
        // check for existing role
        boolean roleExists = roleRepository.existsById(roleId);
        if (!roleExists){
            throw new BadRequestException(MessageConstant.ROLE.ROLE_NOT_FOUND);
        }
        // delete the role , potentially logging the action
        roleRepository.deleteById(roleId);
        log.info("Role with ID {} deleted successfully.",roleId);
    }
    @Transactional
    @Override
    public void updateRole(Long roleId, UpdateRoleRQ request) {
        RoleEntity role = roleRepository.findById(roleId)
                .orElseThrow(() -> new BadRequestException(MessageConstant.ROLE.ROLE_NOT_FOUND));
        log.info("Updating role with ID {}", roleId);

        roleMapper.fromRoleRQ(request, role);

        roleRepository.save(role);
        log.info("Role with ID {} updated successfully.", roleId);
    }

    @Override
    public StructureRS getAllRoles(BaseListingRQ response) {
        Page<RoleEntityInfo> roleEntityInfoPage = roleRepository.findByNameStartsWithAndCode(response.getQuery(), response.getPageable(response.getSort(),response.getOrder()));
        return response(roleEntityInfoPage.getContent(),roleEntityInfoPage);
    }
}
