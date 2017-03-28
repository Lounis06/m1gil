package urouen.sepa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class HomeController {
    @RequestMapping(value="/", method = RequestMethod.GET)
    public @ResponseBody String home() {
        return "<h1>Projet de Langages Web 2</h1>";
    }
}
