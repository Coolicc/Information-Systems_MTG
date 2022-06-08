package app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import app.domain.CardImage;
import app.repository.CardJpaRepo;
import app.repository.DeckJpaRepo;
import app.repository.ManaJpaRepo;
import app.repository.PlayerJpaRepo;
import model.Card;
import model.Deck;
import model.Mana;
import model.Player;

@Controller
@RequestMapping(value="cards")
public class CardController {
	
	@Autowired
	CardJpaRepo cr;
	
	@Autowired
	ManaJpaRepo mr;
	
	@Autowired
	PlayerJpaRepo pr;
	
	@Autowired
	DeckJpaRepo dr;
	
	@RequestMapping(value = "viewCardsPage", method = RequestMethod.GET)
	public String showCardsPage(String page, String search, Model m, HttpServletRequest request) {
		int currPage = Integer.parseInt(page);
		if (search == null) {
			search = "";
		}
		List<Card> cards = cr.searchPage("%"+search.trim()+"%");
		List<Card> cardsPage = new ArrayList<Card>();
		for (int i = (currPage-1)*5; i < (currPage-1)*5+5 && i < cards.size(); i++) {
			cardsPage.add(cards.get(i));
		}
		int maxPage = (int)((cr.countsearch("%"+search.trim()+"%")+4)/5);
		m.addAttribute("page", currPage);
		m.addAttribute("cardsPage", cardsPage);
		m.addAttribute("maxPage", maxPage);
		m.addAttribute("prevPage", currPage-1);
		m.addAttribute("nextPage", currPage+1);
		request.getSession().setAttribute("search", search);
		return "cards";
	}
	
	@RequestMapping(value = "showCollection", method = RequestMethod.GET)
	public String showCollection(String page, String search, Model m, HttpServletRequest request) {
		if (request.getSession().getAttribute("player") == null) {
			return "redirect:/login";
		}
		int currPage = Integer.parseInt(page);
		if (search == null) {
			search = "";
		}
		Player player = (Player) request.getSession().getAttribute("player");
		List<Card> cards = cr.searchCollectionPage("%"+search.trim()+"%", player);
		List<Card> cardsPage = new ArrayList<Card>();
		for (int i = (currPage-1)*5; i < (currPage-1)*5+5 && i < cards.size(); i++) {
			cardsPage.add(cards.get(i));
		}
		int maxPage = (int)((cr.countCollection("%"+search.trim()+"%",player)+4)/5);
		m.addAttribute("page", currPage);
		m.addAttribute("cardsPage", cardsPage);
		m.addAttribute("maxPage", maxPage);
		m.addAttribute("prevPage", currPage-1);
		m.addAttribute("nextPage", currPage+1);
		m.addAttribute("collection", "collection");
		request.getSession().setAttribute("search", search);
		return "cards";
	}
	
	@RequestMapping(value = "showDeck/{id}", method = RequestMethod.GET)
	public String showDeck(@PathVariable("id") int id, Model m, HttpServletRequest request) {
		if (request.getSession().getAttribute("player") == null) {
			return "redirect:/login";
		}
		Deck deck = dr.findOne(id);
		request.getSession().setAttribute("deck", deck);
		return "redirect:/cards/viewCardsPage?search=&page=1";
	}
	
	@RequestMapping(value = "showDecks", method = RequestMethod.GET)
	public String showDecks(Model m, HttpServletRequest request) {
		if (request.getSession().getAttribute("player") == null) {
			return "redirect:/login";
		}
		Player player = (Player) request.getSession().getAttribute("player");
		List<Deck> decks = dr.getPlayersDecks(player);
		m.addAttribute("decks", decks);
		m.addAttribute("newDeck", new Deck());
		return "decks";
	}
	
	@RequestMapping(value = "saveDeck", method = RequestMethod.GET)
	public String saveDeck(Model m, HttpServletRequest request) {
		if (request.getSession().getAttribute("player") == null) {
			return "redirect:/login";
		}
		if (request.getSession().getAttribute("player") == null) {
			return "redirect:/cards/showDecks";
		}
		Deck deck = (Deck) request.getSession().getAttribute("deck");
		Player player = (Player) request.getSession().getAttribute("player");
		deck.setPlayerBean(player);
		player.getDecks().remove(deck);
		player.getDecks().add(deck);
		dr.save(deck);
		request.getSession().removeAttribute("deck");
		return "redirect:/cards/showDecks";
	}
	
	@RequestMapping(value = "addDeck", method = RequestMethod.POST)
	public String addDeck(Model m, HttpServletRequest request, @ModelAttribute("newDeck") Deck deck) {
		if (request.getSession().getAttribute("player") == null) {
			return "redirect:/login";
		}
		Player player = (Player) request.getSession().getAttribute("player");
		player.getDecks().add(deck);
		request.getSession().setAttribute("deck", deck);
		return "redirect:/cards/viewCardsPage?search=&page=1";
	}
	
	@RequestMapping(value="addCardInit", method=RequestMethod.GET)
	public String addCardInit(Model m, HttpServletRequest request) {
		if (request.getSession().getAttribute("player") == null) {
			return "redirect:/login";
		}
		Player p = (Player) request.getSession().getAttribute("player");
		if (p.getAdmin() == 0) {
			return "redirect:/login";
		}
		m.addAttribute("cardImage", new CardImage());
		return "addCard";
	}
	
	@RequestMapping(value="saveCard", method=RequestMethod.POST)
	public String saveCard(Model m, @ModelAttribute("cardImage") CardImage cardImage, HttpServletRequest request) {
		if (request.getSession().getAttribute("player") == null) {
			return "redirect:/login";
		}
		Player p = (Player) request.getSession().getAttribute("player");
		if (p.getAdmin() == 0) {
			return "redirect:/login";
		}
		String url = "redirect:/cards/addCardInit";
		String errors = "";
		Card c = new Card();
		MultipartFile fullImage = cardImage.getImage();
		MultipartFile titleImage = cardImage.getTitleImage();
		if (fullImage != null & titleImage != null) {
			String fileNameFull = "full_" + cardImage.getCardName() + ".jpg";
			String filePath;
			String fileNameTitle = "title_" + cardImage.getCardName() + ".jpg";
			try {
				filePath = "D:\\Programiranje(Java)\\EclipseOxigen\\is2projekatWeb\\WebContent\\img";
				File imageFileFull = new File(filePath, fileNameFull);
				File imageFiletitle = new File(filePath, fileNameTitle);
				fullImage.transferTo(imageFileFull);
				titleImage.transferTo(imageFiletitle);
				
				c.setImage(Files.readAllBytes(imageFileFull.toPath()));
				c.setTitleImage(Files.readAllBytes(imageFiletitle.toPath()));
				c.setCardName(cardImage.getCardName());
				c.setCardSet(cardImage.getCardSet());
				c.setCardText(cardImage.getCardText());
				c.setFlavour(cardImage.getFlavour());
				c.setLoyalty(cardImage.getLoyalty());
				c.setPower(cardImage.getPower());
				c.setToughness(cardImage.getToughness());
				c.setRarity(cardImage.getRarity());
				c.setType(cardImage.getType());
				Set<Mana> manas = new HashSet<Mana>();
				String sManas = cardImage.getManas();
				for (int i = 0; i < sManas.length(); i++) {
					Mana mn = mr.findByShortName(sManas.charAt(i)+"");
					manas.add(mn);
				}
				c.setManas(manas);
				c = cr.save(c);
				for (Mana mn: manas) {
					if (!mn.getCards().contains(c)) {
						mn.getCards().add(c);
						mr.save(mn);
					}
				}
				if (c != null) {
					url = "redirect:/cards/viewCardsPage?search=&page=1";
				}
				else {
					errors = errors + " There was a problem with the server, please try again later.";
				}
				if (errors != "") {
					m.addAttribute("errorP", errors);
					m.addAttribute("cardImage", cardImage);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return url;
	}
	
	@RequestMapping(value = "addToCollection/{id}", method = RequestMethod.GET)
	public String addToCollection(@PathVariable("id") int id, Model m, HttpServletRequest request) {
		if (request.getSession().getAttribute("player") == null) {
			return "redirect:/login";
		}
		Card card = cr.findOne(id);
		Player p = (Player) request.getSession().getAttribute("player");
		p.getCards().add(card);
		card.getPlayers().add(p);
		cr.save(card);
		pr.save(p);
		m.addAttribute("card", card);
		return "card";
	}
	
	@RequestMapping(value = "addToDeck/{id}", method = RequestMethod.GET)
	public String addToDeck(@PathVariable("id") int id, Model m, HttpServletRequest request) {
		if (request.getSession().getAttribute("player") == null) {
			return "redirect:/login";
		}
		if (request.getSession().getAttribute("player") == null) {
			return "redirect:/cards/showDecks";
		}
		Card card = cr.findOne(id);
		Deck d = (Deck) request.getSession().getAttribute("deck");
		card.getDecks().add(d);
		d.getCards().add(card);
		cr.save(card);
		dr.save(d);
		m.addAttribute("card", card);
		return "card";
	}
	
	@RequestMapping(value = "removeFromDeck/{id}", method = RequestMethod.GET)
	public String removeFromDeck(@PathVariable("id") int id, Model m, HttpServletRequest request) {
		if (request.getSession().getAttribute("player") == null) {
			return "redirect:/login";
		}
		if (request.getSession().getAttribute("player") == null) {
			return "redirect:/cards/showDecks";
		}
		Card card = cr.findOne(id);
		Deck d = (Deck) request.getSession().getAttribute("deck");
		card.getDecks().remove(d);
		d.getCards().remove(card);
		System.out.println(d.getCards().size());
		cr.save(card);
		d = dr.save(d);
		System.out.println(d.getCards().size());
		request.getSession().setAttribute("deck", d);
		return "redirect:/cards/viewCardsPage?search=&page=1";
	}
	
	@RequestMapping(value = "deleteCard/{id}", method = RequestMethod.GET)
	public String deleteCard(@PathVariable("id") int id, Model m, HttpServletRequest request) {
		if (request.getSession().getAttribute("player") == null) {
			return "redirect:/login";
		}
		Player p = (Player) request.getSession().getAttribute("player");
		if (p.getAdmin() == 0) {
			return "redirect:/login";
		}
		Card card = cr.findOne(id);
		cr.delete(card);
		return "redirect:/cards/viewCardsPage?search=&page=1";
	}
	
	@RequestMapping(value = "showCard", method = RequestMethod.GET)
	public String showCard(int id, Model m, HttpServletRequest request) {
		Card card = cr.findOne(id);
		m.addAttribute("card", card);
		return "card";
	}
	
	@RequestMapping(value = "get-image/{id}", method = RequestMethod.GET)
	public void getImage(@PathVariable("id") String id, HttpServletResponse response) {
		String[] tokens = id.split("_");
		int cID = Integer.parseInt(tokens[1]);
		Card c = cr.findOne(cID);
		byte[] image;
		if (tokens[0].equals("full")) {
			image = c.getImage();
		}
		else {
			image = c.getTitleImage();
		}
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		try {
			response.getOutputStream().write(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
