package Users;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public abstract class Staff{

    public static HashMap<String,Employee> map = new HashMap<>();

    static void display(){
        for (String username : map.keySet()) {
            System.out.println(username);
        }
    }

    static void remove(String username) {
        if(map.remove(username)== null){
            System.out.println("Aucun employé porte se nom");
        };
    }

    static void add(String username, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        map.put(username, new Employee(username, password));
        System.out.println("Employé ajouté");
    }

    static public void interact() throws NumberFormatException, NoSuchAlgorithmException, IOException{
        try {
            map.get(userInput("Entrez votre nom d'utilisateur :")).interact(userInput("Entrez votre mot de passe : "));
        } catch (NullPointerException e) {
            System.out.println("Le nom d'utilisateur entré est inconnue");
        }
    
    }

    static String userInput(String message) throws IOException{
        System.out.println(message);
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
    

    
}
