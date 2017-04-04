package solution.handlers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.handlers.SessionHandler;
import framework.interfaces.SMSHandler;
import framework.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import room.RoomCommandManager;

import javax.annotation.PostConstruct;

@Service
@SMSAnnotation(trigger = "REGISTER", argumentCount = 1)
public class RegisterHandler implements SMSHandler{
	
	@Autowired
	SessionRepository rep;

//    @Override
//    @PostConstruct
//    public void process(String command, String[] args, RoomCommandManager rcm, SessionHandler session) {
//    	
//    	System.out.println(args[0]);
    	
    	
//    	Session checkSession = rep.getSession(session.getName());
//    	System.out.println(checkSession);
    	
//    	if(session.getName() != null){
//    		session = rep.getSession(session.getName());
//    	} else{
//    		Session newSession = new Session();
//    		newSession.setName(args[0]);
//    		newSession.setGameState(0);
//    		newSession.setRoom("Room1");
//    		session = newSession;
//    		System.out.println(rep);
////    		rep.save(session);
//    	}
    	
	@Override
    public void process(String command, String[] args, RoomCommandManager rcm, SessionHandler sessionHandler) {
        //Create new DB entry or switch to existing session
        //sessionHandler.setRegisteredName(args[0]);

        //Create new session and save to DB
		System.out.println(rep);
        Session session = sessionHandler.getSession();
        session.setName(args[0]);
        session.setGameState(0);
        session.setRoom("Room1");

//        rep.save(session);

        System.out.println("Hello " + args[0] + ", welcome to DragonSMS");
    }
}
