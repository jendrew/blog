package to.ogarne.ogarneblog.web;

import to.ogarne.ogarneblog.model.Post;

/**
 * Created by jedrz on 18.07.2017.
 */
public interface MarkdownParser {

    public Post parse(Post post);
    public Post cutHiddenChars(Post post);
    public String getPlainText(Post post, int limit);
    public Post limit(Post post);
}
