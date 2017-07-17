package to.ogarne.ogarneblog.dao;

import org.springframework.stereotype.Repository;
import to.ogarne.ogarneblog.model.User;

import java.util.List;

/**
 * Created by jedrz on 17.07.2017.
 */

@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
