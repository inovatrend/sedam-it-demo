package hr.sedamit.demo.service;

import hr.sedamit.demo.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleManager {

    List<Role> getAllRoles();

    Optional<Role> getRole(Long roleId);

    Role save(Role role);

    void delete(Long roleId);
}
