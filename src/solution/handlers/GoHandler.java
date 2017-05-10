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
@SMSAnnotation(trigger = "GO", argumentCount = 1)
public class GoHandler implements SMSHandler {
	
	@Autowired
	public static SessionRepository rep;
	
    @Override
    public void process(String commands, String[] args, RoomCommandManager rcm, SessionHandler sessionHandler){
        if(!sessionHandler.isRegistered() || !sessionHandler.isStarted()){
            System.out.println("Error. Please REGISTER then START a game first");
            return;
        }

        Session session = sessionHandler.getSession();
        
        HashMap<String, Object> results;
        
        try{
        	if(session.getRoom().equals(args[0])){
        		System.out.println("You are currently in this room");
        	}else{
        	    sessionHandler.saveState();

        		results = rcm.processRoom(args[0], session.getGameState(), "checkRoom");
        		
        		session.setRoom(args[0]);
        		session.setGameState((Integer)results.get("status"));
        		rep.saveAndFlush(session);
        		
        		System.out.println(results.get("message"));
        		sessionHandler.setInARoom(true);
        	}
        }
        catch (RuntimeException e){
            System.out.println("Error. Invalid Room");
        }

    }
}
