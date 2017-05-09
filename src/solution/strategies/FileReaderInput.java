package solution.strategies;

import framework.Dispatcher;
import solution.interfaces.InputStrategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileReaderInput implements InputStrategy {
    @Override
    public void execute(Dispatcher dispatcher) {
        System.out.println("Specify file name:");
        Scanner sc = new Scanner(System.in);
        String filename = sc.nextLine();

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String currentLine;
            while((currentLine = bufferedReader.readLine()) != null){
                dispatcher.dispatch(currentLine);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
