package to.ogarne.ogarneblog.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import to.ogarne.ogarneblog.mockdata.DatabaseInitializer;

@Profile("dev")
@Controller
public class MockDataController {


    @Autowired
    DatabaseInitializer databaseInitializer;


    @RequestMapping("/init")
    public String getIndex(Model model) {
        databaseInitializer.run();

        return "redirect:/";
    }
}
