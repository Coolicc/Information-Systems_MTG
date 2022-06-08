package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Club;

@Repository
public interface ClubJpaRepo extends JpaRepository<Club, Integer> {
	public Club findByEmail(String email);
}
