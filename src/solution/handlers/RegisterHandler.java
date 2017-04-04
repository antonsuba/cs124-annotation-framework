package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.handlers.SessionHandler;
import framework.interfaces.SMSHandler;
import framework.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import room.RoomCommandManager;

import javax.annotation.PostConstruct;

@SMSAnnotation(trigger = "REGISTER", argumentCount = 1)
public class RegisterHandler implements SMSHandler{

    @Autowired
    SessionRepository rep;

    @Override
    @PostConstruct
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
    }
}
