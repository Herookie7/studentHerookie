package com.herookie.config;

import com.herookie.entity.Admin;
import com.herookie.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> Admin = repository.findByUsername(username);
        return Admin.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("admin not found " + username));

    }
}
