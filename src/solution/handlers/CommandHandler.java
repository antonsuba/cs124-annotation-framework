package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.interfaces.SMSHandler;
import room.RoomCommandManager;

import java.util.Arrays;
import java.util.HashMap;

@SMSAnnotation(trigger = "COMMAND", argumentCount = -1)
public class CommandHandler implements SMSHandler{

	@Override
	public void process(String command, String[] args, RoomCommandManager rcm, Session session) {
		HashMap<String, Object> results = rcm.processRoom(session.getRoom(), session.getGameState(), command + Arrays.toString(args));

		session.setGameState((Integer)results.get("status"));
		System.out.println(results.get("message"));
	}
}
