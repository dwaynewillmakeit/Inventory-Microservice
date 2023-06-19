package com.dwaynewillmakeit.identity_management.util;

import com.dwaynewillmakeit.identity_management.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
@Component
public class LoggedInUserProvider {

    public User getLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {

            System.out.println(authentication);

            return (User) authentication.getPrincipal();
        }
        return null;
    }
}
