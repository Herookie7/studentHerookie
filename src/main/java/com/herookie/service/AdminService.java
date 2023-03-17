package com.herookie.service;

import com.herookie.entity.Admin;
import com.herookie.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        repository.save(admin);
        return "Admin added to system ";
    }
}
