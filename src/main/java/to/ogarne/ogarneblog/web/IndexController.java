package to.ogarne.ogarneblog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import to.ogarne.ogarneblog.model.Category;
import to.ogarne.ogarneblog.service.CategoryService;

/**
 * Created by jedrz on 16.07.2017.
 */


@Controller
public class IndexController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/")
    public String getIndex() {
        Category category = new Category("Dupa", null);
        categoryService.save(category);
        return "index";
    }

}
