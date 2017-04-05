package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.handlers.SessionHandler;
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
	public static SessionRepository rep;
	
	@Override
	public void process(String command, String[] args, RoomCommandManager rcm, SessionHandler sessionHandler) {
		
		Session session = sessionHandler.getSession();
		//String name = session.getName();
		//session = rep.getSession(name);

		//Reset room and game state
		session.setRoom("Room1");
		session.setGameState(0);
		rep.saveAndFlush(session);
		
		HashMap<String, Object> results = rcm.processRoom(session.getRoom(), session.getGameState(), "checkRoom");
		session.setGameState((Integer)results.get("status"));
		System.out.println(results.get("message"));
	}
	

}
