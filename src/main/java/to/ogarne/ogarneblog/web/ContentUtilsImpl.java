package to.ogarne.ogarneblog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import to.ogarne.ogarneblog.model.Post;
import to.ogarne.ogarneblog.service.FileService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class ContentUtilsImpl implements ContentUtils{

    @Autowired
    FileService fileService;


    @Override
    public Post decodeFileIds(Post post) {

        String[] words = post.getBody().split("(?=[\\(\\)])");


        String body = Stream.of(words)
                .map(word -> {
                    if (word.matches("\\(\\/files\\/\\d*")) {
                        return "(/files/" + getFileName(word);
                    } else {
                        return word;
                    }
                }).collect(Collectors.joining());

        post.setBody(body);

        return post;
    }

    private String getFileName(String path) {
        Long id = Long.valueOf(path.substring(8));
        return fileService.findById(id).getFilename();
    }
}
