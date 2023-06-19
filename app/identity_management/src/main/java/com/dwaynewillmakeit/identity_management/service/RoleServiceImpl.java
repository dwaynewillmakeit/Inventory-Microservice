package com.dwaynewillmakeit.identity_management.service;

import com.dwaynewillmakeit.identity_management.dto.RoleUpdateDTO;
import com.dwaynewillmakeit.identity_management.model.Permission;
import com.dwaynewillmakeit.identity_management.model.Role;
import com.dwaynewillmakeit.identity_management.repository.PermissionRepository;
import com.dwaynewillmakeit.identity_management.repository.RoleRepository;
import com.dwaynewillmakeit.identity_management.service.contracts.RoleService;
import com.dwaynewillmakeit.identity_management.util.LoggedInUserProvider;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;


    public List<Role> findAll() {
        val roles = roleRepository.findAll();

        roles.forEach(role -> {
            System.out.println("Permissions for Role " + role.getName());
            System.out.println(Arrays.toString(role.getPermissions().toArray()));
        });
        return roleRepository.findAll();
    }

    public Role save(String name) {

        val role = Role.builder()
                .name(name)
                .createdBy(new LoggedInUserProvider().getLoggedInUser().getId())
                .createdOn(OffsetDateTime.now(ZoneId.of("UTC")))
                .build();

        return roleRepository.save(role);
    }

    public Page<Role> findAll(Pageable pageable) {

        return roleRepository.findAll(pageable);
    }

    public Optional<Role> findById(Long id) {

        return roleRepository.findById(id);
    }

    public boolean updateRole(Long id, RoleUpdateDTO roleUpdateDTO) {

        val role = roleRepository.findById(id).orElse(null);

        if (role == null) {
            return false;
        }

        val permissions = permissionRepository.findAllById(roleUpdateDTO.getPermissionIds());

        if (!role.getName().equalsIgnoreCase("admin")) {

            role.setName(roleUpdateDTO.getRoleName());
        }

        role.setPermissions(new HashSet<>(permissions));

        role.setModifiedBy(new LoggedInUserProvider().getLoggedInUser().getId());
        role.setModifiedOn(OffsetDateTime.now(ZoneId.of("UTC")));

        roleRepository.save(role);

        return true;
    }

    public boolean unAssignPermissions(Long id, List<Long> permissionIds) {

        val role = roleRepository.findById(id).orElse(null);
        if (role == null) {
            return false;
        }
        val permissions = role.getPermissions().stream().filter(permission -> permissionIds.contains(permission.getId())).toList();

        permissions.forEach(role.getPermissions()::remove);

        role.setModifiedBy(new LoggedInUserProvider().getLoggedInUser().getId());
        role.setModifiedOn(OffsetDateTime.now(ZoneId.of("UTC")));

        roleRepository.save(role);
        return true;
    }

    public void deleteRole(Role role) {

        unAssignPermissions(role.getId(), role.getPermissions().stream().map(Permission::getId).toList());

        roleRepository.delete(role);

    }


}
