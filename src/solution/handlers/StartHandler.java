package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.interfaces.SMSHandler;
import framework.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import room.RoomCommandManager;

import java.util.HashMap;

@Component
@SMSAnnotation(trigger = "START")
public class StartHandler implements SMSHandler{
	
	@Autowired
	SessionRepository rep;
	
	Session currentSession = new Session();

	//@PostConstruct
	@Override
	public void process(String command, String[] args, RoomCommandManager rcm, Session session) {
		String name = session.getName();
		//session = rep.getSession(name);

		session.setRoom("Room1");
		session.setGameState(0);

		HashMap<String, Object> results = rcm.processRoom(session.getRoom(), session.getGameState(), "checkRoom");
		session.setGameState((Integer)results.get("status"));
		System.out.println(results.get("message"));
	}
	

}
