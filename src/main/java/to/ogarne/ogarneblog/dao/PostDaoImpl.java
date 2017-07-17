package to.ogarne.ogarneblog.dao;

import org.springframework.stereotype.Repository;
import to.ogarne.ogarneblog.model.Post;

import java.util.List;

/**
 * Created by jedrz on 17.07.2017.
 */

@Repository
public class PostDaoImpl implements PostDao {
    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public Post findById(Long id) {
        return null;
    }

    @Override
    public void save(Post post) {

    }

    @Override
    public void delete(Post post) {

    }
}
