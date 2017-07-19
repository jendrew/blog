package to.ogarne.ogarneblog.web;

import to.ogarne.ogarneblog.model.Post;

import java.util.List;

/**
 * Created by jedrz on 18.07.2017.
 */
public interface MarkdownParser {

    public void parse(Post input);
    public void parseBulk(List<Post> posts);
}
