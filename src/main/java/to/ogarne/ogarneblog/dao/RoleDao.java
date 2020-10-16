package to.ogarne.ogarneblog.dao;

import to.ogarne.ogarneblog.model.Role;

public interface RoleDao extends ExperimentDao<Role, Long> {
    Role findByName(String name);
}
