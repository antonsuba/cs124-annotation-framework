package framework.repositories;

import framework.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>{
	
	@Query("select s from Session s where name = :playerName")
	Session getSession(@Param("playerName") String playerName);
	
}
