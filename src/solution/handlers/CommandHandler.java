package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.interfaces.SMSHandler;
import room.RoomCommandManager;

@SMSAnnotation(trigger = "COMMAND")
public class CommandHandler implements SMSHandler{

	@Override
	public void process(String command, String[] args, RoomCommandManager rcm, Session session) {
		// TODO Auto-generated method stub
		
	}

}
