package com.deileo.basketFinderJava.config;

import com.deileo.basketFinderJava.entity.User;
import com.deileo.basketFinderJava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class JpaAuditingConfiguration implements AuditorAware<User> {

    private final UserRepository userRepo;

    @Autowired
    public JpaAuditingConfiguration(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return userRepo.findByEmail(auth != null ? auth.getName() : "");
    }
}
