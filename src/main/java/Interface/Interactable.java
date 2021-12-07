package Interface;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;

public interface Interactable {

    default String userInput() throws IOException{
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    default String userInput(String message) throws IOException{ // On utilise la surcharge
        System.out.println(message);
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
}
