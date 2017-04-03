package framework;

import framework.annotations.HelperAnnotation;
import framework.annotations.SMSAnnotation;
import framework.interfaces.HelperHandler;
import framework.interfaces.SMSHandler;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dispatcher {

    private HashMap<String, SMSHandler> smsMap = new HashMap<>();
    private ArrayList<HelperHandler> helperArray =  new ArrayList<>();

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
                //HelperAnnotation helperAnnotation = (HelperAnnotation) c.getAnnotation(HelperAnnotation.class);
                helperArray.add((HelperHandler) c.newInstance());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

//    private Object attachValidator(Object o){
//        Class<?> type = o.getClass();
//        ClassLoader classLoader = type.getClassLoader();
//
//        Class<?> byteBuddy = new ByteBuddy().subclass(type)
//                .method(ElementMatchers.isAnnotatedWith(Validate.class))
//                .intercept(MethodDelegation.to(CommandInterceptor.class))
//                .make().load(classLoader).getLoaded();
//
//        Object proxy = null;
//        try {
//            proxy = byteBuddy.newInstance();
//        } catch (InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return proxy;
//    }
//
//    static class CommandInterceptor{
//
//        @RuntimeType
//        public static Object intercept(@Origin Method method,
//                                       @AllArguments Object[] args,
//                                       @SuperCall Callable<?> callable,
//                                       @Super Object superClass)
//                throws Exception {
//
//            Object o = callable.call();
//
//
//
//            return o;
//        }
//    }

    public void dispatch(String command){
        String[] components = command.split("\\s");
        String[] args = new String[]{};
        if(components.length - 1 > 0){
            args = new String[components.length - 1];
            System.arraycopy(components, 1, args, 1, args.length - 1);
        }

        if(validate(components[0], args)){
            return;
        }

        SMSHandler smsHandler = smsMap.get(components[0]);

        if(smsHandler != null){
            smsHandler.process(components[0], args);
        }
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
