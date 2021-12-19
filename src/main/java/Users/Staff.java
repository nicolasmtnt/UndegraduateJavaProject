package Users;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Cette classe statique permet de stocker le compte des vendeurs
 */
public abstract class Staff{

    public static HashMap<String,Employee> map = new HashMap<>();
    public static String filePath = "src/main/ressources/staff.txt";

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
        map.put(username, new Employee(username, password, true));
        System.out.println("Employé ajouté");
    }

    static public void interact() throws NumberFormatException, NoSuchAlgorithmException, IOException{
        try {
            map.get(userInput("Entrez votre nom d'utilisateur : ")).interact(userInput("Entrez votre mot de passe : "));
        } catch (NullPointerException e) {
            System.out.println("Le nom d'utilisateur entré est inconnue");
        }
    
    }

    static String userInput(String message) throws IOException{
        System.out.print(message);
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
    
    /**
    * Enregistre les vendeurs dans un fichier .txt
    */
    public static void writeSave(){

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            for(HashMap.Entry<String,Employee> entry : map.entrySet()){
                bufferedWriter.write(entry.getValue().getClass().getSimpleName()+","+entry.getValue().toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    /**
    * Récupère les articles enregistré dans un .text durant la dernière session
    */
    public static void readSave(){
        Scanner scanner;
        try{
            scanner = new Scanner(new FileInputStream(filePath));
            while(scanner.hasNext()){
                String[] str = scanner.nextLine().split(",");
                switch(str[0]){
                    case "Manager":
                        map.put(str[1], new Manager(str[1],str[2],false));
                        break;
                    case "Employee":
                        map.put(str[1], new Employee(str[1],str[2],false));
                        break;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    
}
