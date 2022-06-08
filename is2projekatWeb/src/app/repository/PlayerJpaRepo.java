package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Player;

@Repository
public interface PlayerJpaRepo extends JpaRepository<Player, Integer> {
	public Player findByUsername(String username);
	public List<Player> findAllByOrderByUsernameAsc();
}
