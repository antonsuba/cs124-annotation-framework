package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.interfaces.SMSHandler;
import room.RoomCommandManager;

@SMSAnnotation(trigger = "REGISTER", argumentCount = 1)
public class RegisterHandler implements SMSHandler{
	
    @Override
    public void process(String command, String[] args, RoomCommandManager rcm, Session session) {
    	session.setName(args[0]);
    	System.out.println(args[0]);
    }
}
