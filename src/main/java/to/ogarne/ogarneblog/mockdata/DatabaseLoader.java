package to.ogarne.ogarneblog.mockdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import to.ogarne.ogarneblog.model.Category;
import to.ogarne.ogarneblog.model.Post;
import to.ogarne.ogarneblog.model.User;
import to.ogarne.ogarneblog.service.CategoryService;
import to.ogarne.ogarneblog.service.PostService;
import to.ogarne.ogarneblog.service.UserService;

/**
 * Created by jedrz on 17.07.2017.
 */
@Component
public class DatabaseLoader implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    PostService postService;


    @Override
    public void run(ApplicationArguments args) throws Exception {


        // Add mock users
        User user = new User("jendrew", "password","Jędrzej Kołtunowicz");
        userService.save(user);

        User aniela = new User("anielka","password","Aniela Masna");
        userService.save(aniela);

        // Add mock categories
        Category programowanie = new Category("programowanie");
        categoryService.save(programowanie);

        Category knigi = new Category("książki");
        categoryService.save(knigi);

        Post post1 = new Post("Pierwszy post", "To jest body pierwszego posta", user, knigi);
        postService.save(post1);

        Post post2 = new Post("Drugi post", "To jest body drugiego posta", aniela, programowanie);
        postService.save(post2);

        Post post3 = new Post("Trzeci post", "To jest body trzeciego posta", user, knigi);
        postService.save(post3);

        Post post4 = new Post("Czwarty post", "To jest body czwartego posta", aniela, programowanie);
        postService.save(post4);



    }
}
