package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.handlers.SessionHandler;
import framework.interfaces.SMSHandler;
import room.RoomCommandManager;

@SMSAnnotation(trigger = "HINT")
public class HintHandler implements SMSHandler{

	@Override
	public void process(String command, String[] args, RoomCommandManager rcm, SessionHandler session) {
		// TODO Auto-generated method stub
		System.out.println("HINT");
		
	}

}
