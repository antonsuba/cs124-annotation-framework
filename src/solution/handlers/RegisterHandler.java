package solution.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.handlers.SessionHandler;
import framework.interfaces.SMSHandler;
import framework.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import room.RoomCommandManager;


@Component
@SMSAnnotation(trigger = "REGISTER", argumentCount = 1)
public class RegisterHandler implements SMSHandler{
	
	@Autowired
	public static SessionRepository rep;
	
	@Override
    public void process(String command, String[] args, RoomCommandManager rcm, SessionHandler sessionHandler) {
        //Create new DB entry or switch to existing session
        //sessionHandler.setRegisteredName(args[0]);

        //Create new session and save to DB
		Session checkSession = rep.getSession(args[0]);
//		System.out.println(rep.getSession(args[0]));
    	
    	if(checkSession != null){
    		sessionHandler.setSession(checkSession);
    		
    		System.out.println("Welcome back " + args[0] + ", you may continue your journey in DragonSMS");
    	} else{
    		Session newSession = new Session();
    		newSession.setName(args[0]);
    		newSession.setGameState(0);
    		newSession.setRoom("Room1");
    		sessionHandler.setSession(newSession);
    		rep.saveAndFlush(newSession);
    		
    		System.out.println("Hello " + args[0] + ", welcome to DragonSMS");
    	}
    	
    }
}
