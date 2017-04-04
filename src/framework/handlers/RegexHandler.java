package framework.handlers;

import framework.annotations.HelperAnnotation;
import framework.interfaces.HelperHandler;

@HelperAnnotation(errorMessage = "Error. Commands must not have numbers and special characters")
public class RegexHandler implements HelperHandler{

    @Override
    public boolean process(String command, String[] args) {
        if(true){
            return true;
        }
        return false;
    }
}
