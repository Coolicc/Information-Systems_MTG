package app.controller;

import java.io.File;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import app.domain.PlayerImage;
import app.repository.CardJpaRepo;
import app.repository.ClubJpaRepo;
import app.repository.DeckJpaRepo;
import app.repository.ManaJpaRepo;
import app.repository.PlayerJpaRepo;
import model.Club;
import model.Player;

@Controller
@RequestMapping(value="/club")
public class ClubController {
	
	@Autowired
	ManaJpaRepo mr;
	
	@Autowired
	PlayerJpaRepo pr;
	
	@Autowired
	DeckJpaRepo dr;
	
	@Autowired
	ClubJpaRepo cr;
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(String email, String password, Model m, HttpServletRequest request) {
		String url = "login";
		String errors = "";
		try {
			Club c = cr.findByEmail(email);
			if (c != null) {
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				if (bc.matches(password, c.getPassword())) {
					url = "index";
					request.getSession().setAttribute("club", c);
				}
				else {
					errors = errors + "Incorrect password.";
				}
			}
			else {
				errors = errors + "Incorrect email.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (errors != "") {
			m.addAttribute("errorC", errors);
		}
		return url;
	}
	
	@RequestMapping(value="saveClub", method=RequestMethod.POST)
	public String savePlayer(Model m, @ModelAttribute("clubReg") Club c, HttpServletRequest request) {
		String url = "register";
		String errors = "";
			try {
				
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				c.setPassword(bc.encode(c.getPassword()));
				c = cr.save(c);
				if (c != null) {
					request.getSession().setAttribute("club", c);
					url = "index";
				}
				else {
					errors = errors + " There was a problem with the server, please try again later.";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (errors != "") {
			m.addAttribute("errorC", errors);
			m.addAttribute("clubReg", c);
			m.addAttribute("playerImage", new PlayerImage());
		}
		return url;
	}
	
	@ModelAttribute("clubReg")
	public Club getClub() {
		return new Club();
	}
}
