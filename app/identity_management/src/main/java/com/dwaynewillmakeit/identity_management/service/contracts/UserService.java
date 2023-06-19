package com.dwaynewillmakeit.identity_management.service.contracts;

import com.dwaynewillmakeit.identity_management.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    void deleteUser(Long id);

    Page<User> findAllUsers(Pageable pageable);

    boolean assignRole(Long id, List<Long> roleIds);

    boolean unAssignRole(Long id, List<Long> roleIds);
}
