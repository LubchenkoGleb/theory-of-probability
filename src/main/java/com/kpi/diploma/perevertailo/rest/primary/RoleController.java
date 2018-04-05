package com.kpi.diploma.perevertailo.rest.primary;

import com.kpi.diploma.perevertailo.model.document.Role;
import com.kpi.diploma.perevertailo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/all")
    private ResponseEntity<Set<Role>> getAllRoles(Principal principal) {

        return ResponseEntity.ok(userRepository.findByEmail(principal.getName()).getRoles());

    }
}
