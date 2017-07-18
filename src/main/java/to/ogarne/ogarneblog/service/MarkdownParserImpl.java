package to.ogarne.ogarneblog.service;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import org.springframework.stereotype.Service;
import to.ogarne.ogarneblog.model.Post;

/**
 * Created by jedrz on 18.07.2017.
 */

@Service
public class MarkdownParserImpl implements MarkdownParser {
    @Override
    public void parse(Post post) {

        Parser parser = Parser.builder().build();
        Node document = parser.parse(post.getBody());
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        post.setBody(renderer.render(document));
    }
}
