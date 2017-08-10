package to.ogarne.ogarneblog.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import to.ogarne.ogarneblog.service.PostService;

@Controller
public class IndexController extends  RootController{

    @Autowired
    PostService postService;

    @RequestMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("recentPosts", postService.findLastXPublishedPosts(5));

        return "index";
    }

}
