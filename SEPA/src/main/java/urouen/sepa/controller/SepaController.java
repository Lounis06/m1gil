package urouen.sepa.controller;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import urouen.sepa.model.SEPA;
@Controller
@RequestMapping("/resume")
public class SepaController {
    @GetMapping
    public @ResponseBody SEPA getSEPAInXML() {
            return new SEPA("Supermarché 012",127);
    }
}
