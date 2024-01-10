package dev.bolohonov.authserver.services;

import dev.bolohonov.authserver.model.Role;
import dev.bolohonov.authserver.model.User;

import java.util.Collection;

public interface RoleService {

    Collection<Role> getRolesForUser(User user);
}
