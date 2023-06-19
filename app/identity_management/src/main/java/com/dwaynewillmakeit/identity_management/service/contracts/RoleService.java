package com.dwaynewillmakeit.identity_management.service.contracts;

import com.dwaynewillmakeit.identity_management.dto.RoleUpdateDTO;
import com.dwaynewillmakeit.identity_management.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();

    Role save(String name);

    Page<Role> findAll(Pageable pageable);

    Optional<Role> findById(Long id);

    boolean updateRole(Long id, RoleUpdateDTO roleUpdateDTO);

    boolean unAssignPermissions(Long id, List<Long> permissionIds);

    void deleteRole(Role role);
}
