package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.interfaces.SMSHandler;
import room.RoomCommandManager;

import java.util.Arrays;

@SMSAnnotation(trigger = "GO", argumentCount = 1)
public class GoHandler implements SMSHandler {
	
    @Override
    public void process(String commands, String[] args, RoomCommandManager rcm, Session session){
        System.out.println(commands);
        System.out.println(Arrays.toString(args));
        
        session.setRoom(Integer.valueOf(args[0]));
    }
}
