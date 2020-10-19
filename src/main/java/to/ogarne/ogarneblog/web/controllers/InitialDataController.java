package to.ogarne.ogarneblog.web.controllers;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import to.ogarne.ogarneblog.initdata.DatabaseInitializer;

@Controller
public class InitialDataController {


    Environment environment;
    DatabaseInitializer databaseInitializer;

    public InitialDataController(Environment environment, DatabaseInitializer databaseInitializer) {
        this.environment = environment;
        this.databaseInitializer = databaseInitializer;
    }

    @RequestMapping("/install")
    public String getIndex(Model model) {
        databaseInitializer.run();

        return "redirect:/";
    }
}
