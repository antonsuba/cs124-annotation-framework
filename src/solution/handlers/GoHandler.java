package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.interfaces.SMSHandler;

@SMSAnnotation(trigger = "GO")
public class GoHandler implements SMSHandler {
    @Override
    public void process(String commands, String[] args) {

    }
}
