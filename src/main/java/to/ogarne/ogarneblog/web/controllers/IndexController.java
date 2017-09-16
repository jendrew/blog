package to.ogarne.ogarneblog.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import to.ogarne.ogarneblog.mockdata.DatabaseLoader;
import to.ogarne.ogarneblog.service.PostService;

@Controller
public class IndexController extends  RootController{

    @Autowired
    PostService postService;

    @Autowired
    DatabaseLoader databaseLoader;

    @RequestMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("recentPosts", postService.findLastXPublishedPosts(5));
        return "index";
    }

    @RequestMapping("/mockdata")
    public String loadMockData(Model model) {
        model.addAttribute("recentPosts", postService.findLastXPublishedPosts(5));
        databaseLoader.run();
        return "redirect:/admin/login";
    }

}
