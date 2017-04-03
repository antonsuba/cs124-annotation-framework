package framework.handlers;

import framework.annotations.HelperAnnotation;
import framework.annotations.Regex;
import framework.interfaces.HelperHandler;

@HelperAnnotation(target = Regex.class)
public class RegexHandler implements HelperHandler{

    @Override
    public void process() {

    }
}
