package framework;

import framework.annotations.HelperAnnotation;
import framework.annotations.SMSAnnotation;
import framework.interfaces.HelperHandler;
import framework.interfaces.SMSHandler;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

import java.util.HashMap;
import java.util.List;

public class Dispatcher {

    private HashMap<String, SMSHandler> smsMap = new HashMap<>();
    private HashMap<Class, HelperHandler> helperMap =  new HashMap<>();

    public Dispatcher(){
        //Scan for SMS Handlers
        ScanResult smsResults = new FastClasspathScanner("solution.handlers").scan();
        List<String> smsScannedClasses = smsResults.getNamesOfClassesWithAnnotation(SMSAnnotation.class);

        for(String smsHandler : smsScannedClasses){
            try {
                Class c = Class.forName(smsHandler);
                SMSAnnotation smsAnnotation = (SMSAnnotation) c.getAnnotation(SMSAnnotation.class);
                smsMap.put(smsAnnotation.trigger(), (SMSHandler) c.newInstance());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        //Scan for Helper Handlers
        ScanResult helperResults = new FastClasspathScanner("framework.handlers").scan();
        List<String> scannedClasses = helperResults.getNamesOfClassesWithAnnotation(HelperAnnotation.class);

        for(String helperHandler : scannedClasses){
            try {
                Class c = Class.forName(helperHandler);
                HelperAnnotation helperAnnotation = (HelperAnnotation) c.getAnnotation(HelperAnnotation.class);
                helperMap.put(helperAnnotation.target(), (HelperHandler) c.newInstance());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public void dispatch(String command){
        SMSHandler smsHandler = smsMap.get(command);

        String[] components = command.split("\\s");
        String[] args = new String[components.length - 1];
        System.arraycopy(components, 1, args, 1, args.length - 1);

        if(smsHandler != null){
            smsHandler.process(components[0], args);
        }
    }
}
