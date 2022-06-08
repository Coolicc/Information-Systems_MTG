package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.reports.CardsDS;
import app.reports.PlayersDS;
import app.repository.CardJpaRepo;
import app.repository.DeckJpaRepo;
import app.repository.ManaJpaRepo;
import app.repository.PlayerJpaRepo;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping(value="/reports")
public class ReportsController {
	
	@Autowired
	CardJpaRepo cr;
	
	@Autowired
	ManaJpaRepo mr;
	
	@Autowired
	PlayerJpaRepo pr;
	
	@Autowired
	DeckJpaRepo dr;
	
	private JRDataSource jrDatasource;
	
	@RequestMapping(value="/cards.pdf", method=RequestMethod.GET)
	public String showReportCards(Model m) {
		CardsDS cds = new CardsDS(cr);
		try {
			jrDatasource = cds.create(null);
			m.addAttribute("datasource", jrDatasource);
			m.addAttribute("format", "pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "rpt_cards";
	}
	
	@RequestMapping(value="/players.pdf", method=RequestMethod.GET)
	public String showReportPlayers(Model m) {
		PlayersDS pds = new PlayersDS(pr);
		try {
			jrDatasource = pds.create(null);
			m.addAttribute("datasource", jrDatasource);
			m.addAttribute("format", "pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "rpt_players";
	}

}
