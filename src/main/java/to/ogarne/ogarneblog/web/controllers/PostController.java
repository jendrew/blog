package to.ogarne.ogarneblog.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import to.ogarne.ogarneblog.model.Post;
import to.ogarne.ogarneblog.service.PostService;
import to.ogarne.ogarneblog.service.UserService;
import to.ogarne.ogarneblog.web.ContentUtils;
import to.ogarne.ogarneblog.web.FlashMessage;
import to.ogarne.ogarneblog.web.MarkdownParser;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jedrz on 18.07.2017.
 */
@PropertySource("app.properties")
@Controller
public class PostController extends RootController {

    @Autowired
    Environment env;

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    ContentUtils contentUtils;

    @Autowired
    MarkdownParser markdownParser;


    /* This method takes  the List of posts form the service and iterates over it
    * shortening content and parsing markdown. I'm not sure if it is a good practice
    * to do it here rather than in some different class*/

    @RequestMapping("/posts")
    public String getPots(Model model) {
        List<Post> posts = postService.findLastXPublishedPosts(10)
                .stream()
                .map(markdownParser::limit)
                .map(markdownParser::parse)
                .collect(Collectors.toList());

        model.addAttribute("posts", posts);

        return "post_list";
    }

    @RequestMapping("/posts/{id}")
    public String getPostDetails(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        markdownParser.cutHiddenChars(post);
        markdownParser.parse(post);
        model.addAttribute("post", post);

        return "post_details";
    }

    // Display form for creating new post
    @RequestMapping("/admin/addPost")
    public String newPostForm(Model model) {
        if (!model.containsAttribute("post")) {
            model.addAttribute("post", new Post());
        }
        model.addAttribute("action", "/admin/addPost");
        model.addAttribute("users", userService.findAll());
        model.addAttribute("categories", categoryService.findAll());

        return "admin/add_post";
    }

    // Process data from creating new post
    @RequestMapping(value = "/admin/addPost", method = RequestMethod.POST)

    public String processNewPostData(@Valid Post post, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) {


        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post", result);
            redirectAttributes.addFlashAttribute("post", post);

            return "redirect:/admin/addPost";
        }

        postService.save(post);
        redirectAttributes.addFlashAttribute("post", post);
        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage("Post successfully added", FlashMessage.Status.SUCCESS));
        return "redirect:" + request.getServletPath();
    }

    // Edit post
    @RequestMapping("/admin/posts/{id}/edit")
    public String editPostForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("post")) {
            Post post = postService.findById(id);
            model.addAttribute("post", post);
        }

        model.addAttribute("action", "/admin/posts/" + id + "/edit");
        model.addAttribute("users", userService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "/admin/add_post";
    }

    // Process data from editing post
    @RequestMapping(value = "/admin/posts/{id}/edit", method = RequestMethod.POST)
    public String processEditPostData(@Valid Post post, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) {


        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post", result);
            redirectAttributes.addFlashAttribute("post", post);

            return "redirect:/admin/posts/" + post.getId() + "/edit";
        }

        contentUtils.decodeFileIds(post);

        postService.save(post);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Post został wyedytowany", FlashMessage.Status.SUCCESS));

        return "redirect:" + request.getServletPath();
    }
}
