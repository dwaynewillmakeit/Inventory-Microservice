package com.dwaynewillmakeit.identity_management.repository;

import com.dwaynewillmakeit.identity_management.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {


}
