package urouen.sepa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import urouen.sepa.model.SEPA;

/**
 * Created by dechapie on 28/03/17.
 */
@Controller
public class SepaDepotController {
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String createNewSepa() {
        return "Formulaire SEPA";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String createNewSepa(@RequestParam SEPA sepa) {
        return "Création d'une nouvelle transaction d'id : "+sepa.getTransaction();
    }
}
