package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the player database table.
 * 
 */
@Entity
@NamedQuery(name="Player.findAll", query="SELECT p FROM Player p")
public class Player implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int playerID;

	private byte admin;
	
	@Lob
	private byte[] avatar;

	private String email;

	private String firstName;

	private String lastName;

	private String password;

	private String username;

	//bi-directional many-to-many association to Card
	@ManyToMany(mappedBy="players", fetch=FetchType.EAGER)
	private Set<Card> cards;

	//bi-directional many-to-one association to Deck
	@OneToMany(mappedBy="playerBean", fetch=FetchType.EAGER)
	private Set<Deck> decks;

	//bi-directional many-to-many association to Tournament
	@ManyToMany
	@JoinTable(
		name="participate"
		, joinColumns={
			@JoinColumn(name="PlayerID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="TournamentID")
			}
		)
	private Set<Tournament> tournaments;

	public Player() {
		this.admin = 0;
	}

	public int getPlayerID() {
		return this.playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public byte getAdmin() {
		return this.admin;
	}

	public void setAdmin(byte admin) {
		this.admin = admin;
	}

	public byte[] getAvatar() {
		return this.avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Card> getCards() {
		return this.cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	public Set<Deck> getDecks() {
		return this.decks;
	}

	public void setDecks(Set<Deck> decks) {
		this.decks = decks;
	}

	public Deck addDeck(Deck deck) {
		getDecks().add(deck);
		deck.setPlayerBean(this);

		return deck;
	}

	public Deck removeDeck(Deck deck) {
		getDecks().remove(deck);
		deck.setPlayerBean(null);

		return deck;
	}

	public Set<Tournament> getTournaments() {
		return this.tournaments;
	}

	public void setTournaments(Set<Tournament> tournaments) {
		this.tournaments = tournaments;
	}

	@Override
	public int hashCode() {
		return playerID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (playerID != other.playerID)
			return false;
		return true;
	}
	
	

}