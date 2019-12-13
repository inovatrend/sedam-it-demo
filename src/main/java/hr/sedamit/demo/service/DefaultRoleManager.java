package hr.sedamit.demo.service;

import hr.sedamit.demo.dao.RoleRepository;
import hr.sedamit.demo.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile("!test")
@Slf4j
public class DefaultRoleManager implements RoleManager {

    private RoleRepository repository;

    public DefaultRoleManager(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> getAllRoles() {
        return repository.findAll();
    }

    @Override
    public Optional<Role> getRole(Long roleId) {
        return repository.findById(roleId);
    }

    @Override
    public Role save(Role role) {
        Role savedRole = repository.save(role);
        return savedRole;
    }

    @Override
    public void delete(Long roleId) {
        repository.deleteById(roleId);
    }




}
