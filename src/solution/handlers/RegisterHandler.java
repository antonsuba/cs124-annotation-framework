package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.handlers.SessionHandler;
import framework.interfaces.SMSHandler;
import framework.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import room.RoomCommandManager;
import solution.expressions.NullExpression;
import solution.interfaces.Expression;


@Component
@SMSAnnotation(trigger = "REGISTER", argumentCount = 1)
public class RegisterHandler implements SMSHandler{
	
	@Autowired
	public static SessionRepository rep;
	
	@Override
    public void process(String command, String[] args, RoomCommandManager rcm, SessionHandler sessionHandler) {
		Session checkSession = rep.getSession(args[0]);
		Expression sessionDoesNotExist = new NullExpression(checkSession);
    	
    	if(sessionDoesNotExist.interpret()){
			Session newSession = new Session();
    		newSession.setName(args[0]);
    		newSession.setGameState(0);
    		newSession.setRoom("Room1");
    		sessionHandler.setSession(newSession);
    		rep.saveAndFlush(newSession);
    		
    		System.out.println("Hello " + args[0] + ", welcome to DragonSMS");
		} else{
			sessionHandler.setSession(checkSession);
    		
    		System.out.println("Welcome back " + args[0] + ", you may continue your journey in DragonSMS");
			System.out.println("You are currently in " + checkSession.getRoom());
			sessionHandler.setStarted(true);
			sessionHandler.setInARoom(true);
    	}

    	sessionHandler.setRegistered(true);
    }
}
