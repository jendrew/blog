package to.ogarne.ogarneblog.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import to.ogarne.ogarneblog.model.Post;
import to.ogarne.ogarneblog.service.PostService;
import to.ogarne.ogarneblog.web.ContentBuilder;
import to.ogarne.ogarneblog.web.MarkdownParser;
import to.ogarne.ogarneblog.web.PostContent;

import java.util.List;

/**
 * Created by jedrz on 18.07.2017.
 */
@PropertySource("app.properties")
@Controller
public class PostController extends  RootController{

    @Autowired
    Environment env;

    @Autowired
    PostService postService;

    @Autowired
    MarkdownParser markdownParser;


    /* This method takes  the List of posts form the service and iterates over it
    * shortening content and parsing markdown. I'm not sure if it is a good practice
    * to do it here rather than in some different class*/

    @RequestMapping("/posts")
    public String getPots(Model model) {
        List<Post> posts = postService.findLastXPublishedPosts(10);

        for (Post post : posts) {
            PostContent content = new ContentBuilder(post.getBody()).limit(400).parse().build();
            post.setBody(content.getContent());
        }

        model.addAttribute("posts", posts);

        return "post_list";
    }

    @RequestMapping("/posts/{id}")
    public String getPostDetails(@PathVariable Long id, Model model){
        Post post = postService.findById(id);
        markdownParser.parse(post);
        model.addAttribute("post", post);

        return "post_details";
    }
}
