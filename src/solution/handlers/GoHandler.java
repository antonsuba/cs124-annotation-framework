package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.handlers.SessionHandler;
import framework.interfaces.SMSHandler;
import room.RoomCommandManager;

import java.util.HashMap;

@SMSAnnotation(trigger = "GO", argumentCount = 1)
public class GoHandler implements SMSHandler {
	
    @Override
    public void process(String commands, String[] args, RoomCommandManager rcm, SessionHandler sessionHandler){
        Session session = sessionHandler.getSession();
        
        HashMap<String, Object> results;
        try{
            results = rcm.processRoom(args[0], session.getGameState(), "checkRoom");
        }
        catch (RuntimeException e){
            System.out.println("Error. Invalid Room");
            return;
        }

        session.setRoom(args[0]);
		session.setGameState((Integer)results.get("status"));
		System.out.println(results.get("message"));
    }
}
