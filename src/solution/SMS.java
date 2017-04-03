package solution;

import framework.Dispatcher;

import java.util.Scanner;

public class SMS {

    public SMS(){
        Dispatcher dispatcher = new Dispatcher();

        Scanner sc = new Scanner(System.in);
        String command = "";
        while(!command.equalsIgnoreCase("exit")){
            command = sc.nextLine();
            dispatcher.dispatch(command);
        }
    }

    public SMS(String filename){

    }
}
