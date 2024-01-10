package dev.bolohonov.authserver.security;

import dev.bolohonov.authserver.model.User;
import dev.bolohonov.authserver.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsHelper {

    private final RoleService roleService;

    @Autowired
    public UserDetailsHelper(RoleService roleService) {
        this.roleService = roleService;
    }

    public UserDetailsImpl buildDetails(User user) {
        List<GrantedAuthority> authorities = roleService.getRolesForUser(user).stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(user.getId(), user.getName(), user.getPassword_hash(), authorities);
    }
}
