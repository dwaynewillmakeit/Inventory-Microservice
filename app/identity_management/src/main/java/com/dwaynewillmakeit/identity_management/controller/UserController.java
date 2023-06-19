package com.dwaynewillmakeit.identity_management.controller;

import com.dwaynewillmakeit.identity_management.model.User;
import com.dwaynewillmakeit.identity_management.service.contracts.UserService;
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
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public Page<User> findAllUsers(@PageableDefault(page = 1,size = 10) Pageable pageable){
        return userService.findAllUsers(pageable);
    }

    @PostMapping("/{id}/roles")
    @PreAuthorize("hasAuthority('role:update') || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> assignRole(@PathVariable Long id,List<Long> roleIds){

       val assigned =  userService.assignRole(id,roleIds);

       if(!assigned){return ResponseEntity.notFound().build();}

       return ResponseEntity.ok().body("Roles assigned");
    }


}
