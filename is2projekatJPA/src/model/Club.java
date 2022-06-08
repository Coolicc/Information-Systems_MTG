package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the club database table.
 * 
 */
@Entity
@NamedQuery(name="Club.findAll", query="SELECT c FROM Club c")
public class Club implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int clubID;

	private String address;
	
	private String clubName;

	private String email;

	private String password;

	//bi-directional many-to-one association to Tournament
	@OneToMany(mappedBy="clubBean", fetch=FetchType.EAGER)
	private Set<Tournament> tournaments;

	public Club() {
	}

	public int getClubID() {
		return this.clubID;
	}

	public void setClubID(int clubID) {
		this.clubID = clubID;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClubName() {
		return this.clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Tournament> getTournaments() {
		return this.tournaments;
	}

	public void setTournaments(Set<Tournament> tournaments) {
		this.tournaments = tournaments;
	}

	public Tournament addTournament(Tournament tournament) {
		getTournaments().add(tournament);
		tournament.setClubBean(this);

		return tournament;
	}

	public Tournament removeTournament(Tournament tournament) {
		getTournaments().remove(tournament);
		tournament.setClubBean(null);

		return tournament;
	}

	@Override
	public int hashCode() {
		return clubID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Club other = (Club) obj;
		if (clubID != other.clubID)
			return false;
		return true;
	}
	
	
}