package to.ogarne.ogarneblog.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import to.ogarne.ogarneblog.model.Category;
import to.ogarne.ogarneblog.service.CategoryService;

import java.util.List;

@Controller
public class RootController {


    @Autowired
    CategoryService categoryService;

    @ModelAttribute("menu")
    public List<Category> getMenu() {
        return categoryService.getCategoriesForMenu();
    }
}
