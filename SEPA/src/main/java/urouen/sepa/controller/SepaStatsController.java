package urouen.sepa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dechapie on 28/03/17.
 */
@Controller
@RequestMapping("/stats")
public class SepaStatsController {
    @GetMapping
    public @ResponseBody String getSepaStats() {
        return "Bonjour";
    }
}
