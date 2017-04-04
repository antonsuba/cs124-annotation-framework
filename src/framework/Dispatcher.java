package framework;

import framework.annotations.HelperAnnotation;
import framework.annotations.SMSAnnotation;
import framework.handlers.SessionHandler;
import framework.interfaces.HelperHandler;
import framework.interfaces.SMSHandler;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import room.RoomCommandManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Dispatcher {

    private HashMap<String, SMSHandler> smsMap = new HashMap<>();
    private ArrayList<HelperHandler> helperArray;

    //private Session session = new Session();
    private SessionHandler sessionHandler = new SessionHandler();
    private RoomCommandManager rcm = new RoomCommandManager();

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

        helperArray = new ArrayList<>(Collections.nCopies(scannedClasses.size(), null));

        for(String helperHandler : scannedClasses){
            try {
                Class c = Class.forName(helperHandler);
                HelperHandler handler = (HelperHandler) c.newInstance();

                int index = handler.getClass().getAnnotation(HelperAnnotation.class).index();
                if(index == -1){
                    helperArray.add(handler);
                }
                else{
                    helperArray.set(index, handler);
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public void dispatch(String command){
        String[] components = command.split("\\s");
        String[] args = new String[]{};
        if(components.length - 1 > 0){
            args = new String[components.length - 1];
            System.arraycopy(components, 1, args, 0, args.length);
        }

        if(!validate(components[0], args)){
            return;
        }

        SMSHandler smsHandler = smsMap.get(components[0].toUpperCase());

        if(smsHandler == null){
            smsHandler = smsMap.get("COMMAND");
        }
//        smsHandler.process(components[0], args, rcm, session);

        smsHandler.process(components[0], args, rcm, sessionHandler);
    }

    private boolean validate(String command, String[] args){

        for(HelperHandler handler : helperArray){
            boolean result = handler.process(command, args);
            if(!result){
                System.out.println(handler.getClass().getAnnotation(HelperAnnotation.class).errorMessage());
                return false;
            }
        }
        return true;
    }
}
