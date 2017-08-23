package to.ogarne.ogarneblog.web;

import com.vladsch.flexmark.Extension;
import com.vladsch.flexmark.ast.BlockQuote;
import com.vladsch.flexmark.ast.Image;
import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.html.AttributeProvider;
import com.vladsch.flexmark.html.AttributeProviderFactory;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.IndependentAttributeProviderFactory;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.options.MutableDataHolder;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import to.ogarne.ogarneblog.model.Post;

import java.util.Arrays;

/**
 * Created by jedrz on 18.07.2017.
 */

@Component
public class MarkdownParserImpl implements MarkdownParser {


    static class BootstrapExtension implements HtmlRenderer.HtmlRendererExtension {
        @Override
        public void rendererOptions(final MutableDataHolder options) {
            // add any configuration settings to options you want to apply to everything, here
        }

        @Override
        public void extend(final HtmlRenderer.Builder rendererBuilder, final String rendererType) {
            rendererBuilder.attributeProviderFactory(BootstrapAttributeProvider.Factory());
        }

        static BootstrapExtension create() {
            return new BootstrapExtension();
        }
    }



    static class BootstrapAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(final Node node, final AttributablePart part, final Attributes attributes) {
            if (node instanceof Image) {
                // Put info in custom attribute instead
                attributes.addValue("class","img-fluid");
                attributes.addValue("class", "ob-cover");
                attributes.addValue("class", "mx-auto");
                attributes.addValue("class", "d-block");
            } else if (node instanceof Link) {
                attributes.addValue("target", "_blank");
            } else if (node instanceof BlockQuote) {
                attributes.addValue("class", "blockquote");
            }
        }

        static AttributeProviderFactory Factory() {
            return new IndependentAttributeProviderFactory() {
                @Override
                public AttributeProvider create(NodeRendererContext context) {
                    //noinspection ReturnOfInnerClass
                    return new BootstrapAttributeProvider();
                }
            };
        }
    }

    @Override
    public Post parse(Post post) {

        MutableDataHolder options = new MutableDataSet();
        options.set(Parser.EXTENSIONS, Arrays.asList(new Extension[] { AutolinkExtension.create(), BootstrapExtension.create() }));


        Parser parser = Parser.builder(options).build();
        Node document = parser.parse(post.getBody());
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        post.setBody(renderer.render(document));
        return post;
    }

    @Override
    public Post cutHiddenChars(Post post) {
        String body = post.getBody();

        post.setBody(StringUtils.delete(body, "@@@"));
        return post;
    }

    @Override
    public Post limit(Post post) {

        String body = post.getBody();

        post.setBody(body.split("@@@")[0]);
        return post;
    }


}
