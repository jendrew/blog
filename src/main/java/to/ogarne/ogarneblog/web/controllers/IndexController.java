package to.ogarne.ogarneblog.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import to.ogarne.ogarneblog.model.User;
import to.ogarne.ogarneblog.service.UserService;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String getIndex() {

        User user = userService.findByUsername("jedrzej");
        return "index";
    }

}
