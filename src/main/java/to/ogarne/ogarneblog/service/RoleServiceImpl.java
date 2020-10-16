package to.ogarne.ogarneblog.service;

import org.springframework.stereotype.Service;
import to.ogarne.ogarneblog.dao.RoleDao;
import to.ogarne.ogarneblog.model.Role;

@Service
public class RoleServiceImpl implements RoleService {

    // == fields ==

    private final RoleDao roleDao;

    // == constructor ==
    public RoleServiceImpl (RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }
}
