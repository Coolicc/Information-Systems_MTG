package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Card;
import model.Player;

@Repository
public interface CardJpaRepo extends JpaRepository<Card, Integer> {
	public Card findByCardName(String cardName);
	
	@Query("SELECT c FROM Card c WHERE c.cardName LIKE :search")
	public List<Card> searchPage(@Param("search") String search);
	
	public List<Card> findAllByOrderByCardSetAsc();
	
	@Query("SELECT COUNT(c) FROM Card c WHERE c.cardName LIKE :search")
	public int countsearch(@Param("search") String search);
	
	@Query("SELECT COUNT(c) FROM Card c, Player p WHERE c.cardName LIKE :search AND p = :player AND c MEMBER OF p.cards")
	public int countCollection(@Param("search") String search, @Param("player") Player player);
	
	@Query("SELECT c FROM Card c, Player p WHERE c.cardName LIKE :search AND p = :player AND c MEMBER OF p.cards")
	public List<Card> searchCollectionPage(@Param("search") String search, @Param("player") Player player);
}
