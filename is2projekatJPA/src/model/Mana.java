package model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Set;


/**
 * The persistent class for the mana database table.
 * 
 */
@Entity
@NamedQuery(name="Mana.findAll", query="SELECT m FROM Mana m")
public class Mana implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int manaID;

	private String name;

	private String shortName;

	//bi-directional many-to-many association to Card
	@ManyToMany(mappedBy="manas", fetch=FetchType.EAGER)
	private Set<Card> cards;

	public Mana() {
	}

	public int getManaID() {
		return this.manaID;
	}

	public void setManaID(int manaID) {
		this.manaID = manaID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Set<Card> getCards() {
		return this.cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	@Override
	public int hashCode() {
		return manaID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mana other = (Mana) obj;
		if (manaID != other.manaID)
			return false;
		return true;
	}
	
	
}