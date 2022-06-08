package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Card;
import model.Mana;

public interface ManaJpaRepo extends JpaRepository<Mana, Integer> {
	public Mana findByShortName(String shortName);
}
