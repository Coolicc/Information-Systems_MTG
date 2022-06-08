package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the deck database table.
 * 
 */
@Entity
@NamedQuery(name="Deck.findAll", query="SELECT d FROM Deck d")
public class Deck implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int deckID;

	private String deckName;

	@Lob
	private String description;

	//bi-directional many-to-many association to Card
	@ManyToMany(mappedBy="decks", fetch=FetchType.EAGER)
	private Set<Card> cards;

	//bi-directional many-to-one association to Player
	@ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn(name="Player")
	private Player playerBean;

	public Deck() {
		cards = new HashSet<Card>();
	}

	public int getDeckID() {
		return this.deckID;
	}

	public void setDeckID(int deckID) {
		this.deckID = deckID;
	}

	public String getDeckName() {
		return this.deckName;
	}

	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Card> getCards() {
		return this.cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	public Player getPlayerBean() {
		return this.playerBean;
	}

	public void setPlayerBean(Player playerBean) {
		this.playerBean = playerBean;
	}
	
	@Override
	public int hashCode() {
		return deckID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deck other = (Deck) obj;
		if (deckID != other.deckID)
			return false;
		return true;
	}
	
	
}