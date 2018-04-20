package urouen.sepa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody String home(Model model) {
		return "<h1>SEPA<H1><p>Page d'accueil</p>";
	}
}
