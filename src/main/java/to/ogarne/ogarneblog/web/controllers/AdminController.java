package to.ogarne.ogarneblog.web.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import to.ogarne.ogarneblog.model.Post;
import to.ogarne.ogarneblog.model.Role;
import to.ogarne.ogarneblog.model.User;
import to.ogarne.ogarneblog.service.*;
import to.ogarne.ogarneblog.web.dto.CategoryWrapper;
import to.ogarne.ogarneblog.web.dto.FlashMessage;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * Created by jedrz on 17.07.2017.
 */

//TODO: Implment the ability to preview posts without it being indexed by sitemap.xml


@Controller
public class AdminController extends RootController {

    private final PostService postService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final FileService fileService;
    private final RoleService roleService;

    public AdminController(PostService postService, UserService userService, CategoryService categoryService, FileService fileService, RoleService roleService) {
        this.postService = postService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.fileService = fileService;
        this.roleService = roleService;
    }

    // Display login screen
    @RequestMapping("/admin/login")
    public String logInForm(Model model, HttpServletRequest request) {
        try {
            Object flash = request.getSession().getAttribute("flash");
            model.addAttribute("flash", flash);
            request.getSession().removeAttribute("flash");

            model.addAttribute("user", new User());
        } catch (Exception ex) {
            // Do nothing
        }

        return "admin/login";
    }


    // Display admin's control panel
    @RequestMapping("/admin")
    public String getAdminPanel(Model model) {
        List<Post> posts = postService.findAll();
        Collections.reverse(posts);
        model.addAttribute("posts", posts);
        return "admin/panel";
    }

    // Display edit menu panel
    @RequestMapping("/admin/editmenu")
    public String editMenuForm(Model model) {
        CategoryWrapper categories = new CategoryWrapper();
        categories.setCategories(categoryService.findAll());
        model.addAttribute("wrapper", categories);
        return "admin/edit_menu";
    }

    // Process edited menu info
    @RequestMapping(value = "/admin/editmenu", method = RequestMethod.POST)
    public String processEditMenuData(@Valid @ModelAttribute CategoryWrapper wrapper, Model model) {
        categoryService.saveInBulk(wrapper.getCategories());
        return "redirect:/admin/editmenu";
    }

    // Get the image list
    @RequestMapping("/admin/images")
    public String showImageList(Model model) {
        model.addAttribute("images", fileService.findAllImages());

        return "admin/image_list";
    }

    // Get the user list
    @RequestMapping("/admin/editusers")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }

    // Get the user list with user details at the end
    @RequestMapping(value = "/admin/editusers/{id}")
    public String displayUserDetails(Model model, @PathVariable Long id, RedirectAttributes attributes) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("userDetails", userService.findById(id));
        model.addAttribute("roles", roleService.findAll());
        return "admin/users";
    }

    // Process editing user data
    // TODO: Implement userDetails validation
    @RequestMapping(value = "/admin/editusers", method = RequestMethod.POST)
    public String processEditUserData(@ModelAttribute User userDetails,
                                      RedirectAttributes redirectAttributes,
                                      HttpServletRequest request) {

        updateUser(userDetails);

        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage("User edited successfully", FlashMessage.Status.SUCCESS));

        return "redirect:/admin/editusers/";
    }

    // Finds user in the database and changes user data accordingly to
    // the data passed as an argument.
    private void updateUser(User user) {
        User existingUser = userService.findById(user.getId());
        if (existingUser != null) {

            // set new username
            existingUser.setUsername(user.getUsername());

            // set new fullName
            existingUser.setFullName(user.getFullName());

            // set new role
            Role role = roleService.findById(user.getRole().getId());
            existingUser.setRole(role);

            // if user changed password, encode it and update
            if (!user.getPassword().equals("")) {
                String password = user.getPassword();
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
                existingUser.setPassword(passwordEncoder.encode(password));
            }

            userService.save(existingUser);
        }
    }

}
