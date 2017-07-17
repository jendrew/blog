package to.ogarne.ogarneblog.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import to.ogarne.ogarneblog.model.Post;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by jedrz on 17.07.2017.
 */

@Repository
public class PostDaoImpl implements PostDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Post> findAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Post> criteria = builder.createQuery(Post.class);
        criteria.from(Post.class);
        List<Post> posts = session.createQuery(criteria).getResultList();
        session.close();

        return posts;
    }

    @Override
    public List<Post> findLastXPosts(int numberOfPosts) {

        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Post> criteria = builder.createQuery(Post.class);
        Root<Post> root =  criteria.from(Post.class);

        // order descending by date
        criteria.orderBy(builder.desc(root.get("date")));
        List<Post> posts = session.createQuery(criteria)
                // limit number of returned posts to @param numberOfPosts
                .setMaxResults(numberOfPosts)
                .getResultList();
        session.close();

        return posts;
    }

    @Override
    public Post findById(Long id) {
        return null;
    }

    @Override
    public void save(Post post) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(post);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Post post) {

    }
}
