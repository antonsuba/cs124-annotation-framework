package solution;

import framework.Dispatcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String currentLine;

            Dispatcher dispatcher = new Dispatcher();
            while((currentLine = bufferedReader.readLine()) != null){
                dispatcher.dispatch(currentLine);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
