package to.ogarne.ogarneblog.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import to.ogarne.ogarneblog.model.Role;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class RoleDaoImpl extends ExperimentDaoImpl<Role, Long> implements RoleDao {

    // == fields ==
    private SessionFactory sessionFactory;

    // == constructors ==
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //== methods ==
    @Override
    public Role findByName(String name) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
        Root<Role> root = criteria.from(Role.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("name"), name));
        Role role = session.createQuery(criteria).getSingleResult();
        session.close();
        return role;

    }

}
