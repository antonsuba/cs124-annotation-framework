package app;

import framework.repositories.SessionRepository;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import solution.SMS;
import solution.handlers.CommandHandler;
import solution.handlers.GoHandler;
import solution.handlers.RegisterHandler;
import solution.handlers.StartHandler;

public class Tester {
	
    public static void main(String args[]){
    	AbstractApplicationContext ctx;
    	
        // load application context files
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml", "applicationContext-jpa.xml");
    	SessionRepository sr = (SessionRepository)ctx.getBean("sessionRepository");
    	RegisterHandler.rep = sr;
    	StartHandler.rep = sr;
    	CommandHandler.rep = sr;
    	GoHandler.rep = sr;
    	
        SMS sms = new SMS();
    }
}
