package fr.univ.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.model.CVS;

@RestController
public class CVIController {
	@RequestMapping(value = "/root")
	public @ResponseBody String getRoot() {
		return "Hello world from spring root v4.3.9 !";
	}
	
	@RequestMapping(value = "/list/{id}")
	@ResponseBody
	public String getListId (@PathVariable("id") int id) {
		if (id < 2) {
			return "Demande limitée à 2 cvs";
		} else {
			return new CVS("bombadil","tom").toString();
		}
	}
}
