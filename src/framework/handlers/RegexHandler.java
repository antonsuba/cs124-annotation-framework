package framework.handlers;

import framework.annotations.HelperAnnotation;
import framework.interfaces.HelperHandler;

import java.util.Arrays;

@HelperAnnotation(index = 0, errorMessage = "Error. Commands must not have numbers and special characters")
public class RegexHandler implements HelperHandler{

    @Override
    public boolean process(String command, String[] args) {
        //return command.matches("/^[A-Za-z]+$/") && Arrays.toString(args).matches("/^[A-Za-z]+$/");
        return true;
    }
}
