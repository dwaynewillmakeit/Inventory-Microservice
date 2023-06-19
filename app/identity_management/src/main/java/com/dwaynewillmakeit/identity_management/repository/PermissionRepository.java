package com.dwaynewillmakeit.identity_management.repository;

import com.dwaynewillmakeit.identity_management.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {

//    @Query("SELECT p FROM Permission p JOIN p.roles r WHERE r.id = :roleId")
//    List<Permission> findByRoleId(@Param("roleId") Long roleId);
//
//    @Query("SELECT p FROM Permission p JOIN p.roles r WHERE r = :role")
//    List<Permission> findPermissionByRole(@Param("role") Role role);
}
