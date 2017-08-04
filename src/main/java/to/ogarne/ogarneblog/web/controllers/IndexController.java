package to.ogarne.ogarneblog.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import to.ogarne.ogarneblog.model.User;
import to.ogarne.ogarneblog.dao.StorageDaoImpl;
import to.ogarne.ogarneblog.service.UserService;

@Controller
public class IndexController extends  RootController{

    @Autowired
    UserService userService;

    @Autowired
    StorageDaoImpl fssService;

    @RequestMapping("/")
    public String getIndex() {
        fssService.init();
        User user = userService.findByUsername("jedrzej");
        return "index";
    }

}
