package to.ogarne.ogarneblog.dao;

import to.ogarne.ogarneblog.model.Category;

import java.util.List;

/**
 * Created by jedrz on 17.07.2017.
 */
public interface CategoryDao {
    List<Category> findAll();
    Category findById(Long id);
    void save (Category category);
    void delete(Category category);
}
