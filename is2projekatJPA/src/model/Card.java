package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the card database table.
 * 
 */
@Entity
@NamedQuery(name="Card.findAll", query="SELECT c FROM Card c")
public class Card implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cardID;

	private String cardName;

	private String cardSet;

	@Lob
	private String cardText;

	@Lob
	private String flavour;
	
	@Lob
	private byte[] image;

	private int loyalty;

	private String power;

	private String rarity;
	
	@Lob
	private byte[] titleImage;

	private String toughness;

	private String type;

	//bi-directional many-to-many association to Player
	@ManyToMany (fetch=FetchType.EAGER)
	@JoinTable(
		name="collection"
		, joinColumns={
			@JoinColumn(name="CardID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="PlayerID")
			}
		)
	private Set<Player> players;

	//bi-directional many-to-many association to Deck
	@ManyToMany (fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(
		name="indeck"
		, joinColumns={
			@JoinColumn(name="CardID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="DeckID")
			}
		)
	private Set<Deck> decks;

	//bi-directional many-to-many association to Mana
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="manacost"
		, joinColumns={
			@JoinColumn(name="CardID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ManaID")
			}
		)
	private Set<Mana> manas;

	public Card() {
	}

	public int getCardID() {
		return this.cardID;
	}

	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	public String getCardName() {
		return this.cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardSet() {
		return this.cardSet;
	}

	public void setCardSet(String cardSet) {
		this.cardSet = cardSet;
	}

	public String getCardText() {
		return this.cardText;
	}

	public void setCardText(String cardText) {
		this.cardText = cardText;
	}

	public String getFlavour() {
		return this.flavour;
	}

	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getLoyalty() {
		return this.loyalty;
	}

	public void setLoyalty(int loyalty) {
		this.loyalty = loyalty;
	}

	public String getPower() {
		return this.power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getRarity() {
		return this.rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public byte[] getTitleImage() {
		return this.titleImage;
	}

	public void setTitleImage(byte[] titleImage) {
		this.titleImage = titleImage;
	}

	public String getToughness() {
		return this.toughness;
	}

	public void setToughness(String toughness) {
		this.toughness = toughness;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public Set<Deck> getDecks() {
		return this.decks;
	}

	public void setDecks(Set<Deck> decks) {
		this.decks = decks;
	}

	public Set<Mana> getManas() {
		return this.manas;
	}

	public void setManas(Set<Mana> manas) {
		this.manas = manas;
	}
	
	@Override
	public int hashCode() {
		return cardID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (cardID != other.cardID)
			return false;
		return true;
	}
	
	
}