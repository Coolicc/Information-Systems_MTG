package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the tournament database table.
 * 
 */
@Entity
@NamedQuery(name="Tournament.findAll", query="SELECT t FROM Tournament t")
public class Tournament implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tournamentID;

	@Lob
	private String description;

	private int maxContestants;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	private String tournamentName;

	//bi-directional many-to-many association to Player
	@ManyToMany(mappedBy="tournaments", fetch=FetchType.EAGER)
	private Set<Player> players;

	//bi-directional many-to-one association to Club
	@ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn(name="Club")
	private Club clubBean;

	public Tournament() {
	}

	public int getTournamentID() {
		return this.tournamentID;
	}

	public void setTournamentID(int tournamentID) {
		this.tournamentID = tournamentID;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxContestants() {
		return this.maxContestants;
	}

	public void setMaxContestants(int maxContestants) {
		this.maxContestants = maxContestants;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTournamentName() {
		return this.tournamentName;
	}

	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}

	public Set<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public Club getClubBean() {
		return this.clubBean;
	}

	public void setClubBean(Club clubBean) {
		this.clubBean = clubBean;
	}

	@Override
	public int hashCode() {
		return tournamentID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tournament other = (Tournament) obj;
		if (tournamentID != other.tournamentID)
			return false;
		return true;
	}
	
	

}