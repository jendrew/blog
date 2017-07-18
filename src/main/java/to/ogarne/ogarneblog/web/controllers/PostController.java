package to.ogarne.ogarneblog.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import to.ogarne.ogarneblog.model.Post;
import to.ogarne.ogarneblog.service.PostService;

import java.util.List;

/**
 * Created by jedrz on 18.07.2017.
 */

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @RequestMapping("/posts")
    public String getPots(Model model) {
        List<Post> posts = postService.findLastXPosts(3);

        model.addAttribute("posts", postService.findLastXPosts(3));

        return "post_list";
    }

    @RequestMapping("/posts/{id}")
    public String getPostDetails(@PathVariable Long id, Model model){
        model.addAttribute("post", postService.findById(id));

        return "post_details";
    }
}
