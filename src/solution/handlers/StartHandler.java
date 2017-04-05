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
		if(!sessionHandler.isRegistered()){
			System.out.println("Error. Please REGISTER first");
			return;
		}

		Session session = sessionHandler.getSession();

		//Reset room and game state
		session.setRoom("Room1");
		session.setGameState(0);
		
		HashMap<String, Object> results = rcm.processRoom(session.getRoom(), session.getGameState(), "checkRoom");
		session.setGameState((Integer)results.get("status"));
		rep.saveAndFlush(session);
		
		System.out.println(results.get("message"));

		sessionHandler.setStarted(true);
	}
}
