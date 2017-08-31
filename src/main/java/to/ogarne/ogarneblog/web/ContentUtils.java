package to.ogarne.ogarneblog.web;

import to.ogarne.ogarneblog.model.Post;

import java.util.List;

public interface ContentUtils {

    Post decodeFileIds(Post post);
    List<String> getImagePaths(Post post);

}
