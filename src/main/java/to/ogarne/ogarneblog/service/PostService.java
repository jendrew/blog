package to.ogarne.ogarneblog.service;

import to.ogarne.ogarneblog.model.Post;

import java.util.List;

/**
 * Created by jedrz on 17.07.2017.
 */
public interface PostService {

    List<Post> findAll();
    List<Post> findLastXPublishedPosts(int numberOfPosts);
    Post findById(Long id);
    void save (Post post);
    Long saveWithId(Post post);
    void delete(Post post);
}
