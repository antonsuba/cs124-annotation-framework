package framework.handlers;

import framework.annotations.HelperAnnotation;
import framework.interfaces.HelperHandler;

@HelperAnnotation(index = 0, errorMessage = "Error. Commands cannot be empty and must not have numbers and special characters")
public class RegexHandler implements HelperHandler{

    @Override
    public boolean process(String command, String[] args) {
        return command.matches("[a-zA-Z]+\\.?");
    }
}
