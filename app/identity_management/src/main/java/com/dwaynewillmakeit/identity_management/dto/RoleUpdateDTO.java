package com.dwaynewillmakeit.identity_management.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleUpdateDTO {
    private String roleName;
    private List<Long> permissionIds;
}
