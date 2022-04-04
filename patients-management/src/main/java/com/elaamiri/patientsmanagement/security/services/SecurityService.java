package com.elaamiri.patientsmanagement.security.services;

import com.elaamiri.patientsmanagement.security.entities.AppRole;
import com.elaamiri.patientsmanagement.security.entities.AppUser;
import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    AppUser saveNewUser(String username, String password, String passwordConfirmation);
    AppRole saveNewRole(String roleName, String roleDescription);
    void addRoleToUser(AppUser appUser, String roleName);
    void removeRoleOfUser();
}
