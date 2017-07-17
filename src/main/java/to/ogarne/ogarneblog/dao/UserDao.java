package to.ogarne.ogarneblog.dao;

import to.ogarne.ogarneblog.model.User;

import java.util.List;

/**
 * Created by jedrz on 17.07.2017.
 */
public interface UserDao {
    List<User> findAll();
    User findById(Long id);
    void save (User user);
    void delete(User user);
}
