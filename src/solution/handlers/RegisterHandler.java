package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.interfaces.SMSHandler;

@SMSAnnotation(trigger = "REGISTER", argumentCount = 1)
public class RegisterHandler implements SMSHandler{
    @Override
    public void process(String command, String[] args) {

    }
}
