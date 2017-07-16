package to.ogarne.ogarneblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jedrz on 16.07.2017.
 */

@Controller
public class IndexController {

    @RequestMapping("/")
    public String getIndex() {
        return "index";
    }

}
