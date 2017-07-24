package to.ogarne.ogarneblog.web;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import org.springframework.stereotype.Component;
import to.ogarne.ogarneblog.model.Post;

import java.util.List;

/**
 * Created by jedrz on 18.07.2017.
 */

@Component
public class MarkdownParserImpl implements MarkdownParser {
    @Override
    public void parse(Post post) {

        Parser parser = Parser.builder().build();
        Node document = parser.parse(post.getBody());
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        post.setBody(renderer.render(document));
    }

    @Override
    public void parseBulk(List<Post> posts) {
        for (Post post : posts) {
            parse(post);
        }
    }




}
