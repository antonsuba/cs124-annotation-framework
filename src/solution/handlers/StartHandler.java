package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.interfaces.SMSHandler;
import room.RoomCommandManager;

@SMSAnnotation(trigger = "START")
public class StartHandler implements SMSHandler{

	@Override
	public void process(String command, String[] args, RoomCommandManager rcm, Session session) {
		//SessionRepository r = new SessionRepository();
		
	}

}
