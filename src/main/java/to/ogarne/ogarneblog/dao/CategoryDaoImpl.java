package to.ogarne.ogarneblog.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import to.ogarne.ogarneblog.model.Category;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by jedrz on 17.07.2017.
 */

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    SessionFactory sessionFactory;

    // Returns list of all categories
    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
        criteria.from(Category.class);
        List<Category> categories = session.createQuery(criteria).getResultList();
        session.close();

        return categories;
    }

    // Returns category by given id
    @Override
    public Category findById(Long id) {
        return null;
    }


    @Override
    public void save(Category category) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(category);
        session.getTransaction().commit();
        session.close();
    }


    public void saveInBulk(List<Category> categories) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        for (Category cat : categories) {
            session.saveOrUpdate(cat);
        }

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Category> getCategoriesForMenu() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
        Root root = criteria.from(Category.class);
        criteria.where(builder.isNotNull(root.get("positionInMenu")));
        criteria.orderBy(builder.asc(root.get("positionInMenu")));
        List<Category> categories = session.createQuery(criteria).getResultList();
        session.close();

        return categories;

    }


    @Override
    public void delete(Category category) {

    }


}
