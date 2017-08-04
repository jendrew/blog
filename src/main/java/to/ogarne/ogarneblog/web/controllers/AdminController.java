    package to.ogarne.ogarneblog.web.controllers;

    import org.hibernate.exception.ConstraintViolationException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.ModelAndView;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;
    import to.ogarne.ogarneblog.model.Post;
    import to.ogarne.ogarneblog.model.User;
    import to.ogarne.ogarneblog.service.CategoryService;
    import to.ogarne.ogarneblog.service.PostService;
    import to.ogarne.ogarneblog.service.UserService;
    import to.ogarne.ogarneblog.web.CategoryWrapper;
    import to.ogarne.ogarneblog.web.FlashMessage;

    import javax.servlet.http.HttpServletRequest;
    import javax.validation.Valid;
    import java.util.Collections;
    import java.util.List;

    /**
     * Created by jedrz on 17.07.2017.
     */

    @Controller
    public class AdminController extends  RootController {

        @Autowired
        PostService postService;

        @Autowired
        UserService userService;

        @Autowired
        CategoryService categoryService;

        // Login admin

        @RequestMapping("/admin/login")
        public String logInForm(Model model, HttpServletRequest request) {
            try {
                Object flash = request.getSession().getAttribute("flash");
                model.addAttribute("flash", flash);
                model.addAttribute("user", new User());
                request.getSession().removeAttribute("flash");
            } catch (Exception ex) {
                // Do nothing
            }

            return "admin/login";
        }


        // Admin's control panel
        @RequestMapping("/admin")
        public String getAdminPanel (Model model){
            List<Post> posts = postService.findAll();
            Collections.reverse(posts);
            model.addAttribute("posts", posts);
            return "/admin/panel";
        }


        // Display form for creating new post
        @RequestMapping("/admin/addPost")
        public String newPostForm(Model model) {
            if (!model.containsAttribute("post")) {
                model.addAttribute("post", new Post());
            }
            model.addAttribute("action","/admin/addPost");
            model.addAttribute("users", userService.findAll());
            model.addAttribute("categories", categoryService.findAll());

            return "admin/add_post";
        }

        // Process data from creating new post
        @RequestMapping(value = "/admin/addPost", method = RequestMethod.POST)

        public String processNewPostData(@Valid Post post, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) {


            if (result.hasErrors()) {
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post", result);
                redirectAttributes.addFlashAttribute("post",post);

                return "redirect:/admin/addPost";
            }

            postService.save(post);
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("Post successfully added", FlashMessage.Status.SUCCESS));
            return "redirect:" + request.getServletPath();
        }

        // Edit post
        @RequestMapping("/admin/posts/{id}/edit")
        public String editPostForm(@PathVariable Long id, Model model){
            if (!model.containsAttribute("post")) {
                Post post = postService.findById(id);
                model.addAttribute("post", post);
            }
            model.addAttribute("action","/admin/posts/" + id + "/edit");
            model.addAttribute("users", userService.findAll());
            model.addAttribute("categories", categoryService.findAll());
            return "/admin/add_post";
        }

        // Process data from editing post
        @RequestMapping(value = "/admin/posts/{id}/edit", method = RequestMethod.POST)
        public String processEditPostData(@Valid Post post, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) {


            if (result.hasErrors()) {
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post", result);
                redirectAttributes.addFlashAttribute("post",post);

                return "redirect:/admin/posts/"+ post.getId() +"/edit";
            }

            postService.save(post);

            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Post został wyedytowany", FlashMessage.Status.SUCCESS));

            return "redirect:" + request.getServletPath();
        }

        // Edit menu
        @RequestMapping("/admin/editmenu")
        public String editMenuForm(Model model){
            CategoryWrapper categories = new CategoryWrapper();
            categories.setCategories(categoryService.findAll());
            model.addAttribute("wrapper", categories);
            return "/admin/edit_menu";
        }



        // Get the result of menu editing
        @RequestMapping(value = "/admin/editmenu", method = RequestMethod.POST)
        public String processEditMenuData(@Valid @ModelAttribute CategoryWrapper wrapper, Model model) {
            categoryService.saveInBulk(wrapper.getCategories());
            return "redirect:/admin/editmenu";
        }

        // Handles the case of user entering the same orderInMenu in two different places.
        @ExceptionHandler({ConstraintViolationException.class})
        public ModelAndView databaseError(HttpServletRequest req, Exception ex) {
            if (req.getServletPath().equals("/admin/editmenu")) {
                ModelAndView mav = new ModelAndView();
                mav.addObject("flash", new FlashMessage("Liczby nie mogą się powtarzać", FlashMessage.Status.FAILURE));
                CategoryWrapper wrapper = new CategoryWrapper();
                wrapper.setCategories(categoryService.findAll());
                mav.addObject("wrapper", wrapper);
                mav.setViewName("/admin/edit_menu");
                return mav;

            }

            return null;
        }
    }
