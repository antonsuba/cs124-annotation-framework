package solution.handlers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.interfaces.SMSHandler;
import framework.repositories.SessionRepository;
import room.RoomCommandManager;

@Component
@SMSAnnotation(trigger = "START")
public class StartHandler implements SMSHandler{
	
	@Autowired
	SessionRepository rep;
	
	Session currentSession = new Session();

	//@PostConstruct
	@Override
	public void process(String command, String[] args, RoomCommandManager rcm, Session session) {
		String name = session.getName();
		session = rep.getSession(name);
	}
	

}
