package framework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import framework.entity.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>{
	
	@Query("select s from session where name = :playerName")
	public Session getSession(@Param("playerName")String playerName);
	
}
