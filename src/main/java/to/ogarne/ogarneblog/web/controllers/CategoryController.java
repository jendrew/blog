package to.ogarne.ogarneblog.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import to.ogarne.ogarneblog.model.Category;
import to.ogarne.ogarneblog.service.CategoryService;
import to.ogarne.ogarneblog.service.PostService;
import to.ogarne.ogarneblog.web.MarkdownParser;
import to.ogarne.ogarneblog.web.Pagination;
import to.ogarne.ogarneblog.web.Parseable;

import java.util.List;
import java.util.stream.Collectors;


@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    PostService postService;

    @Autowired
    MarkdownParser markdownParser;

    @RequestMapping("/categories/{categoryName}")
    public String getPostsFromCategory(@PathVariable String categoryName, Model model,
                                       @PageableDefault(value=10, page = 0) Pageable pageable) {
        Category category = categoryService.findByName(categoryName);

        List<Parseable> posts = postService.findPostsInCategory(pageable, category.getId()).stream()
                .map(markdownParser::limit)
                .map(markdownParser::parse)
                .collect(Collectors.toList());


        model.addAttribute("pagination", new Pagination(pageable, postService.getCount(true, category.getId())));
        model.addAttribute("posts", posts);


        return "post_list";


    }
}
