package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Card;
import model.Deck;
import model.Player;

@Repository
public interface DeckJpaRepo extends JpaRepository<Deck, Integer> {
	@Query("SELECT d FROM Deck d, Player p WHERE p = :player AND d MEMBER OF p.decks")
	public List<Deck> getPlayersDecks(@Param("player") Player player);
}
