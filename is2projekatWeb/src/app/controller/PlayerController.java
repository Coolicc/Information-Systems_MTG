package app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import app.domain.PlayerImage;
import app.repository.CardJpaRepo;
import app.repository.DeckJpaRepo;
import app.repository.ManaJpaRepo;
import app.repository.PlayerJpaRepo;
import model.Club;
import model.Player;

@Controller
@RequestMapping(value="/player")
public class PlayerController {
	
	@Autowired
	CardJpaRepo cr;
	
	@Autowired
	ManaJpaRepo mr;
	
	@Autowired
	DeckJpaRepo dr;
	
	@Autowired
	PlayerJpaRepo pr;
	
	@RequestMapping(value="savePlayer", method=RequestMethod.POST)
	public String savePlayer(Model m, @ModelAttribute("playerImage") PlayerImage playerImage, HttpServletRequest request) {
		String url = "register";
		String errors = "";
		MultipartFile file = playerImage.getAvatar();
		Player p=new Player();
		if (null != file) {
			String fileName = playerImage.getUsername() + ".jpg";
			String filePath;
			try {
				filePath = "D:\\Programiranje(Java)\\EclipseOxigen\\is2projekatWeb\\WebContent\\img";
				File imageFile = new File(filePath, fileName);

				file.transferTo(imageFile);
				
				
				p.setAvatar(Files.readAllBytes(imageFile.toPath()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		p.setAdmin((byte)0);
		p.setEmail(playerImage.getEmail());
		p.setFirstName(playerImage.getFirstName());
		p.setLastName(playerImage.getLastName());
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		p.setPassword(bc.encode(playerImage.getPassword()));
		p.setUsername(playerImage.getUsername());
		p = pr.save(p);
		if (p != null) {
			request.getSession().setAttribute("player", p);
			url = "index";
		}
		else {
			errors = errors + " There was a problem with the server, please try again later.";
		}
		if (errors != "") {
			m.addAttribute("errorP", errors);
			m.addAttribute("playerImage", playerImage);
			m.addAttribute("clubReg", new Club());
		}
		return url;
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(String username, String password, Model m, HttpServletRequest request) {
		String url = "login";
		String errors = "";
		try {
			Player p = pr.findByUsername(username);
			if (p != null) {
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				if (bc.matches(password, p.getPassword())) {
					url = "index";
					request.getSession().setAttribute("player", p);
				}
				else {
					errors = errors + "Incorrect password.";
				}
			}
			else {
				errors = errors + "Incorrect username.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (errors != "") {
			m.addAttribute("errorP", errors);
		}
		return url;
	}
	
	@ModelAttribute("playerImage")
	public PlayerImage getPlayerImage() {
		return new PlayerImage();
	}
	
	@RequestMapping(value = "get-image/{id}", method = RequestMethod.GET)
	public void getKnjiga(@PathVariable("id") int playerId, HttpServletResponse response) {
		Player p = pr.getOne(playerId);
		byte[] slika = p.getAvatar();
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		try {
			response.getOutputStream().write(slika);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
