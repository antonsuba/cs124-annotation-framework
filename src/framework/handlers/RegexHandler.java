package framework.handlers;

import framework.annotations.HelperAnnotation;
import framework.interfaces.HelperHandler;

@HelperAnnotation()
public class RegexHandler implements HelperHandler{

    @Override
    public boolean process(String command, String[] args) {
        Boolean valid = false;
        String formattedCommand = command;

        return valid;
    }
}
