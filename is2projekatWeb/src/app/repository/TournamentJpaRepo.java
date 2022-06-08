package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Tournament;

@Repository
public interface TournamentJpaRepo extends JpaRepository<Tournament, Integer> {

}
