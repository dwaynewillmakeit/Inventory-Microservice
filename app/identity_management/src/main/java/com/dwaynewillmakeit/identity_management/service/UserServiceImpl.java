package com.dwaynewillmakeit.identity_management.service;

import com.dwaynewillmakeit.identity_management.model.User;
import com.dwaynewillmakeit.identity_management.repository.RoleRepository;
import com.dwaynewillmakeit.identity_management.repository.UserRepository;
import com.dwaynewillmakeit.identity_management.service.contracts.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user) {
        // Perform any required validation or business logic
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    @Override
    public boolean assignRole(Long id, List<Long> roleIds) {

        val user = userRepository.findById(id).orElse(null);
        if(user==null){return false;}

        val roles = roleRepository.findAllById(roleIds);

        user.setRoles(new HashSet<>());
        user.setRoles(new HashSet<>(roles));

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean unAssignRole(Long id, List<Long> roleIds) {

        val user = userRepository.findById(id).orElse(null);
        if(user==null){return false;}

        val roles = user.getRoles().stream().filter(role -> roleIds.contains(role.getId()));

        roles.forEach(role -> user.getRoles().remove(role));

        userRepository.save(user);

        return true;
    }
}
