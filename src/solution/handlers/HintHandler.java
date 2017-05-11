package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.handlers.SessionHandler;
import framework.interfaces.SMSHandler;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import room.RoomCommandManager;

import java.lang.reflect.Method;
import java.util.List;

@SMSAnnotation(trigger = "HINT")
public class HintHandler implements SMSHandler{

	@Override
	public void process(String command, String[] args, RoomCommandManager rcm, SessionHandler sessionHandler) {
//		if(!sessionHandler.isInARoom()){
//			System.out.println("Error. Please GO in a room first");
//			return;
//		}

		System.out.println("Here is a list of things you can do: ");
		System.out.println("(although some of these may only be called depending on your state)");
		Session session = sessionHandler.getSession();
		
		ScanResult roomResult = new FastClasspathScanner("room."+session.getRoom()).scan();
		List<String> allClasses = roomResult.getNamesOfAllClasses();	
		
		for(String cl : allClasses){
			
			try {
				Class c = Class.forName(cl);
				
				Method[] ml = c.getDeclaredMethods();
				
				for(Method m : ml){
					System.out.println(" - " + m.getName());
				}
				System.out.println(" - GO Room#");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
