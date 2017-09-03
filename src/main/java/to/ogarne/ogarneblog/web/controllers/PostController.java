package to.ogarne.ogarneblog.web.controllers;

import com.github.slugify.Slugify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import to.ogarne.ogarneblog.model.Post;
import to.ogarne.ogarneblog.service.CategoryService;
import to.ogarne.ogarneblog.service.PostService;
import to.ogarne.ogarneblog.service.UserService;
import to.ogarne.ogarneblog.web.*;

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

    @Autowired
    Slugify slugify;

    @Autowired
    CategoryService categoryService;



    /* This method takes  the List of posts form the service and iterates over it
    * shortening content and parsing markdown. I'm not sure if it is a good practice
    * to do it here rather than in some different class*/

    @RequestMapping("/posts")
    public String getPosts(Model model, @PageableDefault(value=1, page = 0) Pageable pageable) {

        List<Parseable> posts = postService.findLastXPublishedPosts(pageable)
                .stream()
                .map(markdownParser::limit)
                .map(markdownParser::parse)
                .collect(Collectors.toList());



        model.addAttribute("pagination", new Pagination(pageable, postService.getCount(true)));
        model.addAttribute("posts", posts);

        return "post_list";
    }

    @RequestMapping("/posts/{id:\\d+}-{slug}")
    public String getPostDetails(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        markdownParser.cutHiddenChars(post);
        String description = markdownParser.getPlainText(post, 300);
        model.addAttribute("description", description);
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

    public String processNewPostData(@Valid Post post, BindingResult result,
                                     RedirectAttributes redirectAttributes, HttpServletRequest request) {


        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post", result);
            redirectAttributes.addFlashAttribute("post", post);


            return "redirect:/admin/addPost";
        }

        if (post.getSlug().length() < 1) {
            post.setSlug(slugify.slugify(post.getTitle()));
        }

        contentUtils.decodeFileIds(post);

        List<String> imagePaths = contentUtils.getImagePaths(post);
        if (imagePaths.size() > 0) {
            post.setImagePaths(imagePaths);
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
    public String processEditPostData(@Valid Post post, BindingResult result,
                                      RedirectAttributes redirectAttributes, HttpServletRequest request) {


        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post", result);
            redirectAttributes.addFlashAttribute("post", post);

            return "redirect:/admin/posts/" + post.getId() + "/edit";
        }

        if (post.getSlug().length() < 1) {
            post.setSlug(slugify.slugify(post.getTitle()));
        }

        contentUtils.decodeFileIds(post);

        List<String> imagePaths = contentUtils.getImagePaths(post);
        if (imagePaths != null && imagePaths.size() > 0) {
            post.setImagePaths(imagePaths);
        }

        postService.save(post);

        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage("Post został wyedytowany", FlashMessage.Status.SUCCESS));

        return "redirect:" + request.getServletPath();
    }

    // Process data from editing post
    @RequestMapping(value = "/admin/posts/{id}/delete", method = RequestMethod.POST)
    public String processDeletePost(@PathVariable Long id, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (!request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/logout";
        }

        Post post = postService.findById(id);
        request.getSession().setAttribute("postToUndelete", post);
        postService.delete(post);

        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage("Post został usunięty. Chcesz cofnąć?", FlashMessage.Status.UNDELETE));

        return "redirect:/admin/";
    }

    @RequestMapping(value = "/admin/posts/undelete", method = RequestMethod.POST)
    public String processUndeletePost(RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (!request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/logout";
        }

        Post post = (Post) request.getSession().getAttribute("postToUndelete");
        post.setId(null);

        if (post != null) {
            postService.save(post);
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("Cofnęliśmy usunięcie posta", FlashMessage.Status.SUCCESS));

        } else {
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("Nie udało się przywrócić posta", FlashMessage.Status.FAILURE));

        }

        return "redirect:/admin";
    }
}
