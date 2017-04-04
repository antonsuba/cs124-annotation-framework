package framework.handlers;

import framework.annotations.HelperAnnotation;
import framework.annotations.SMSAnnotation;
import framework.interfaces.HelperHandler;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

import java.util.List;

@HelperAnnotation(index = 1, errorMessage = "Error. Not enough arguments")
public class ArgumentCountHandler implements HelperHandler {
    @Override
    public boolean process(String command, String[] args) {

        ScanResult smsResults = new FastClasspathScanner("solution.handlers").scan();
        List<String> smsScannedClasses = smsResults.getNamesOfClassesWithAnnotation(SMSAnnotation.class);

        for(String smsHandler : smsScannedClasses){
            Class c = null;
            try {
                c = Class.forName(smsHandler);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            assert c != null;
            SMSAnnotation smsAnnotation = (SMSAnnotation) c.getAnnotation(SMSAnnotation.class);
            if(command.equalsIgnoreCase(smsAnnotation.trigger()) && args.length == smsAnnotation.argumentCount()){
                return true;
            }
        }
        return false;
    }
}
