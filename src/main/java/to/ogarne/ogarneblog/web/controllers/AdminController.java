package to.ogarne.ogarneblog.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import to.ogarne.ogarneblog.model.Post;
import to.ogarne.ogarneblog.service.CategoryService;
import to.ogarne.ogarneblog.service.PostService;
import to.ogarne.ogarneblog.service.UserService;

import javax.validation.Valid;

/**
 * Created by jedrz on 17.07.2017.
 */

@Controller
public class AdminController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    // Display form for creating new post
    @RequestMapping("/admin/addPost")
    public String newPostForm(Model model) {
        if (!model.containsAttribute("post")) {
            model.addAttribute("post", new Post());
        }
        model.addAttribute("users", userService.findAll());
        model.addAttribute("categories", categoryService.findAll());

        return "admin/add_post";
    }

    // Process data from creating new post
    @RequestMapping(value = "/admin/addPost", method = RequestMethod.POST)
    public String newPostForm(@Valid Post post, BindingResult result, RedirectAttributes redirectAttributes) {


        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post", result);
            redirectAttributes.addFlashAttribute("post",post);

            return "redirect:/admin/addPost";
        }

        postService.save(post);


        return "redirect:/posts";
    }

}
