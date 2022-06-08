package app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.domain.PlayerImage;
import app.repository.CardJpaRepo;
import app.repository.DeckJpaRepo;
import app.repository.ManaJpaRepo;
import app.repository.PlayerJpaRepo;
import model.Club;

@Controller
@RequestMapping(value = "/")
public class UtilController {
	
	@Autowired
	CardJpaRepo cr;
	
	@Autowired
	ManaJpaRepo mr;
	
	@Autowired
	PlayerJpaRepo pr;
	
	@Autowired
	DeckJpaRepo dr;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goHome() {
		return "index";
	}
	
	@RequestMapping(value="/test2", method=RequestMethod.GET)
	public String test2() {
		return "test2";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(Model m) {
		m.addAttribute("playerImage", new PlayerImage());
		m.addAttribute("clubReg", new Club());
		return "register";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		String url = "index";
		request.getSession().removeAttribute("player");
		request.getSession().removeAttribute("club");
		request.getSession().removeAttribute("deck");
		return url;
	}
}
