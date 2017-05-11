package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.handlers.SessionHandler;
import framework.interfaces.SMSHandler;
import framework.repositories.SessionRepository;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import room.RoomCommandManager;
import solution.expressions.OrExpression;
import solution.interfaces.Expression;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

@Component
@SMSAnnotation(trigger = "COMMAND", argumentCount = -1)
public class CommandHandler implements SMSHandler{
	
	@Autowired
	public static SessionRepository rep;
	
	@Override
	public void process(String command, String[] args, RoomCommandManager rcm, SessionHandler sessionHandler) {
		Expression registeredAndStarted = new OrExpression(!sessionHandler.isRegistered(),!sessionHandler.isStarted());
		
		if(registeredAndStarted.interpret()){
			System.out.println("Error. Please use the REGISTER or START command first");
			return;
		}

		Session session = sessionHandler.getSession();
		HashMap<String, Object> results;
		
		String arguments = " ";
		for (String arg : args) arguments += arg;
		
		ScanResult roomResult = new FastClasspathScanner("room."+session.getRoom()).scan();
		List<String> allClasses = roomResult.getNamesOfAllClasses();	
		boolean commandFound = false;
		
		for(String cl : allClasses){
			
			try {
				Class c = Class.forName(cl);
				
				Method[] ml = c.getDeclaredMethods();
				
				for(Method m : ml){
					if(command.equals(m.getName())){
						commandFound = true;
					}
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try{
			if(commandFound){
				results = rcm.processRoom(session.getRoom(), session.getGameState(), command + arguments);
				
				session.setGameState((Integer)results.get("status"));
				rep.saveAndFlush(session);
				
				System.out.println(results.get("message"));
			} else{
				System.out.println("Invalid command");
			}
		}
		catch (RuntimeException e){
			System.out.println("Error invalid command or arguments");
			return;
		}
	}
}
