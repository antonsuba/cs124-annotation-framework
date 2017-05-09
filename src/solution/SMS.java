package solution;

import framework.Dispatcher;
import solution.strategies.CommandLineInput;
import solution.strategies.FileReaderInput;

public class SMS {

    public SMS(String inputMode){
        Dispatcher dispatcher = new Dispatcher();
        InputContext inputContext = new InputContext();

        if(inputMode.equalsIgnoreCase("TEXTFILE")){
            inputContext.setInputStrategy(new FileReaderInput());
        }
        else if(inputMode.equalsIgnoreCase("COMMANDLINE")){
            inputContext.setInputStrategy(new CommandLineInput());
        }

        inputContext.executeInputStrategy(dispatcher);
    }
}
