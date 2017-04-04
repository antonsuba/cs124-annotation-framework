package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.handlers.SessionHandler;
import framework.interfaces.SMSHandler;
import room.RoomCommandManager;

import java.util.Arrays;

@SMSAnnotation(trigger = "GO", argumentCount = 1)
public class GoHandler implements SMSHandler {
	
    @Override
<<<<<<< HEAD
    public void process(String commands, String[] args, RoomCommandManager rcm, SessionHandler session){
=======
    //public void process(String commands, String[] args, RoomCommandManager rcm, Session session){
    public void process(String commands, String[] args, RoomCommandManager rcm, SessionHandler sessionHandler){
        Session session = sessionHandler.getSession();
>>>>>>> refs/remotes/origin/master
        System.out.println(commands);
        System.out.println(Arrays.toString(args));
//        session.setRoom(args[0]);
    }
}
