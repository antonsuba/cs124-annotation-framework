package solution.handlers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.handlers.SessionHandler;
import framework.interfaces.SMSHandler;
import framework.repositories.SessionRepository;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> refs/remotes/origin/master
import room.RoomCommandManager;

import javax.annotation.PostConstruct;

@Component
@SMSAnnotation(trigger = "REGISTER", argumentCount = 1)
public class RegisterHandler implements SMSHandler{
<<<<<<< HEAD
	
	@Autowired
	SessionRepository rep;
	
=======

    @Autowired
    SessionRepository rep;

>>>>>>> refs/remotes/origin/master
    @Override
    @PostConstruct
<<<<<<< HEAD
    public void process(String command, String[] args, RoomCommandManager rcm, SessionHandler session) {
    	
    	System.out.println(args[0]);
    	
    	
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
    	
=======
    public void process(String command, String[] args, RoomCommandManager rcm, SessionHandler sessionHandler) {
        //Create new DB entry or switch to existing session
        //sessionHandler.setRegisteredName(args[0]);

        //Create new session and save to DB
        Session session = sessionHandler.getSession();
        session.setName(args[0]);
        session.setGameState(0);
        session.setRoom("Room1");

        rep.save(session);

        System.out.println("Hello " + args[0] + ", welcome to DragonSMS");
>>>>>>> refs/remotes/origin/master
    }
}
