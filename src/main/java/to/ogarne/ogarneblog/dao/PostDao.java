package to.ogarne.ogarneblog.dao;

import to.ogarne.ogarneblog.model.Post;

import java.util.List;

/**
 * Created by jedrz on 17.07.2017.
 */
public interface PostDao extends GenericDao<Post,Long> {
    List<Post> findLastXPublishedPosts(int numberOfPosts);
    Long saveWithId(Post post);

}
