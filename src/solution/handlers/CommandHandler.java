package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.handlers.SessionHandler;
import framework.interfaces.SMSHandler;
import framework.repositories.SessionRepository;
import room.RoomCommandManager;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SMSAnnotation(trigger = "COMMAND", argumentCount = -1)
public class CommandHandler implements SMSHandler{
	
	@Autowired
	public static SessionRepository rep;
	
	@Override
	public void process(String command, String[] args, RoomCommandManager rcm, SessionHandler sessionHandler) {
		Session session = sessionHandler.getSession();
		HashMap<String, Object> results;

		String arguments = " ";
		for (String arg : args) arguments += arg;

		try{
			results = rcm.processRoom(session.getRoom(), session.getGameState(), command + arguments);
		}
		catch (RuntimeException e){
			System.out.println("Error invalid command or arguments");
			return;
		}

		session.setGameState((Integer)results.get("status"));
		System.out.println(session.getGameState());
		rep.saveAndFlush(session);
		
		System.out.println(results.get("message"));
	}
}
