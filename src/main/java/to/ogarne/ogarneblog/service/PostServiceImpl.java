package to.ogarne.ogarneblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import to.ogarne.ogarneblog.dao.PostDao;
import to.ogarne.ogarneblog.model.Post;
import to.ogarne.ogarneblog.web.MarkdownParser;

import java.util.List;

/**
 * Created by jedrz on 17.07.2017.
 */

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostDao postDao;

    @Autowired
    MarkdownParser markdownParser;

    @Override
    public List<Post> findAll() {
        return postDao.findAll();
    }

    @Override
    public List<Post> findLastXPosts(int numberOfPosts) {
        return postDao.findLastXPosts(numberOfPosts);
    }

    @Override
    public Post findById(Long id) {
        return postDao.findById(id);
    }

    @Override
    public void save(Post post) {

        postDao.save(post);

    }

    @Override
    public void delete(Post post) {
        postDao.delete(post);
    }
}
