package to.ogarne.ogarneblog.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import to.ogarne.ogarneblog.model.Page;
import to.ogarne.ogarneblog.service.PageService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RootController {


    @Autowired
    PageService pageService;

    @ModelAttribute("menuItems")
    public List<Page> getMenu() {
        return pageService.getPagesForMenu();
    }

    @ModelAttribute("referer")
    public String getReferer(HttpServletRequest request) {
        return request.getHeader("referer");
    }
}
