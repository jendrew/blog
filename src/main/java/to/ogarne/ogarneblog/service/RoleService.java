package to.ogarne.ogarneblog.service;

import to.ogarne.ogarneblog.model.Role;

import java.util.List;

public interface  RoleService {
    Role findByName(String name);
    void save(Role role);
    List<Role> findAll();
    Role findById(Long id);
}
