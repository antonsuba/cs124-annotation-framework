package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.interfaces.SMSHandler;

import java.util.Arrays;

@SMSAnnotation(trigger = "GO")
public class GoHandler implements SMSHandler {
    @Override
    public void process(String commands, String[] args){
        System.out.println(commands);
        System.out.println(Arrays.toString(args));
    }
}
