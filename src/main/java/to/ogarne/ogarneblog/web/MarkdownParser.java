package to.ogarne.ogarneblog.web;

import to.ogarne.ogarneblog.model.Post;

/**
 * Created by jedrz on 18.07.2017.
 */
public interface MarkdownParser {

    public void parse(Post input);
}
