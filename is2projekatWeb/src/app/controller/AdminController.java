package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import app.repository.CardJpaRepo;
import app.repository.DeckJpaRepo;
import app.repository.ManaJpaRepo;
import app.repository.PlayerJpaRepo;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	CardJpaRepo cr;
	
	@Autowired
	ManaJpaRepo mr;
	
	@Autowired
	PlayerJpaRepo pr;
	
	@Autowired
	DeckJpaRepo dr;

}
