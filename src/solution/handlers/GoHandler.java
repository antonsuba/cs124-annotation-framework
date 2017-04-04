package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.interfaces.SMSHandler;
import room.RoomCommandManager;

@SMSAnnotation(trigger = "GO", argumentCount = 1)
public class GoHandler implements SMSHandler {
	
    @Override
    public void process(String commands, String[] args, RoomCommandManager rcm, Session session){
        session.setRoom(Integer.valueOf(args[0]));
    }
}
