package solution.strategies;

import framework.Dispatcher;
import solution.interfaces.InputStrategy;

import java.util.Scanner;

public class CommandLineInput implements InputStrategy{
    @Override
    public void execute(Dispatcher dispatcher) {
        Scanner sc = new Scanner(System.in);
        String command = "";
        while(!command.equalsIgnoreCase("exit")){
            command = sc.nextLine();
            dispatcher.dispatch(command);
        }
    }
}
