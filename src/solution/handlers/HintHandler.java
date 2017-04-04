package solution.handlers;

import framework.annotations.SMSAnnotation;
<<<<<<< HEAD
import framework.entity.Session;
=======
>>>>>>> refs/remotes/origin/master
import framework.handlers.SessionHandler;
import framework.interfaces.SMSHandler;
import room.RoomCommandManager;

@SMSAnnotation(trigger = "HINT")
public class HintHandler implements SMSHandler{

	@Override
<<<<<<< HEAD
	public void process(String command, String[] args, RoomCommandManager rcm, SessionHandler session) {
=======
	public void process(String command, String[] args, RoomCommandManager rcm, SessionHandler sessionHandler) {
>>>>>>> refs/remotes/origin/master
		// TODO Auto-generated method stub
		System.out.println("HINT");
		
	}

}
