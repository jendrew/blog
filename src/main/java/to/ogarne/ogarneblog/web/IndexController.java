package to.ogarne.ogarneblog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import to.ogarne.ogarneblog.service.CategoryService;
import to.ogarne.ogarneblog.service.PostService;

/**
 * Created by jedrz on 16.07.2017.
 */


@Controller
public class IndexController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    PostService postService;

    @RequestMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("posts", postService.findLastXPosts(3));

        return "post_list";
    }

}
