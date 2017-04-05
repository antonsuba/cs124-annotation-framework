package app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import framework.entity.Session;
import framework.repositories.SessionRepository;
import solution.SMS;
import solution.handlers.CommandHandler;
import solution.handlers.RegisterHandler;
import solution.handlers.StartHandler;

public class Tester {
	
    public static void main(String args[]){
    	AbstractApplicationContext ctx;
    	
        // load application context files
        ctx = new ClassPathXmlApplicationContext(new String []{"applicationContext.xml", "applicationContext-jpa.xml"});
    	SessionRepository sr = (SessionRepository)ctx.getBean("sessionRepository");
    	RegisterHandler.rep = sr;
    	StartHandler.rep = sr;
    	CommandHandler.rep = sr;
    	
        SMS sms = new SMS();
    }
}
