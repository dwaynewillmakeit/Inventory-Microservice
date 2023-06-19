package com.dwaynewillmakeit.identity_management.controller;

import com.dwaynewillmakeit.identity_management.dto.RoleUpdateDTO;
import com.dwaynewillmakeit.identity_management.model.Permission;
import com.dwaynewillmakeit.identity_management.model.Role;
import com.dwaynewillmakeit.identity_management.repository.PermissionRepository;
import com.dwaynewillmakeit.identity_management.service.RoleServiceImpl;
import com.dwaynewillmakeit.identity_management.service.contracts.RoleService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private PermissionRepository permissionRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('role:read') || hasAuthority('ROLE_ADMIN')")
    public Page<Role> fetchRoles(@PageableDefault(page = 1,size = 10) Pageable pageable) {


       return roleService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('role:read') || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Role> fetchRoleById(@PathVariable Long id){

        val role = roleService.findById(id);

        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('role:write') || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Role> createRole(String name){

        return ResponseEntity.ok().body(roleService.save(name));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('role:update') || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody RoleUpdateDTO roleUpdateDTO){

        val assigned = roleService.updateRole(id,roleUpdateDTO);

        if(assigned){
            val role = roleService.findById(id).orElseThrow();
            return ResponseEntity.ok(role);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('role:delete') || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteRole(@PathVariable Long id){

        val role = roleService.findById(id).orElse(null);


        if(role==null){return ResponseEntity.notFound().build();}

        if(role.getName().equalsIgnoreCase("Admin")){

            return ResponseEntity
                    .badRequest()
                    .body("Cannot delete the Admin Role from the system");
        }

        roleService.deleteRole(role);

        return ResponseEntity.ok().body("Deleted");

    }

    @GetMapping("/permissions")
    @PreAuthorize("hasAuthority('role:read') || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Permission>> fetchPermissions(){

        return ResponseEntity.ok().body(permissionRepository.findAll());
    }
}
