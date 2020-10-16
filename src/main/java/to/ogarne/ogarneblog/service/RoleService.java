package to.ogarne.ogarneblog.service;

import to.ogarne.ogarneblog.model.Role;

public interface  RoleService {
    Role findByName(String name);
    void save(Role role);
}
