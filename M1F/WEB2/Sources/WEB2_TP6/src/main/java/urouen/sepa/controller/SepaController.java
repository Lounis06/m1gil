package urouen.sepa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import urouen.sepa.model.SEPA;

@Controller
public class SepaController {
    @RequestMapping(value = "/resume", method = RequestMethod.GET)
    public @ResponseBody SEPA getSEPA() {
        return new SEPA();
    }
}
