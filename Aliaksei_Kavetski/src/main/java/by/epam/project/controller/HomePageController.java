package by.epam.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/index.html")
@Controller
public class HomePageController {
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
