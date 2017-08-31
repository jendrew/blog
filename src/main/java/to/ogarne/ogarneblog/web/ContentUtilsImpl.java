package to.ogarne.ogarneblog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import to.ogarne.ogarneblog.model.Post;
import to.ogarne.ogarneblog.service.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    @Override
    public List<String> getImagePaths(Post post) {
        List<String> paths = new ArrayList<>();
        String body = post.getBody();
        Pattern pattern = Pattern.compile("(/files\\/[a-zA-Z0-9//_.]*)");

        Matcher matcher = pattern.matcher(body);

        while (matcher.find()) {
            paths.add(matcher.group(0));
        }

        return paths;
    }

    private String getFileName(String path) {
        Long id = Long.valueOf(path.substring(8));
        return fileService.findById(id).getFilename();
    }
}
