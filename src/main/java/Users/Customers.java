package Users;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Cette classe statique permet de stocker le compte des utilisateurs
 */
public class Customers{
    private static HashMap<String, Customer> map = new HashMap<>();
    public static String filePath = "src/main/ressources/customer.txt";

    public static void add(String username, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        map.put(username, new Customer(username, password, true));
        System.out.println("Compte crée");
    }

    public static void remove(String username) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if(map.remove(username) == null){
            System.out.println("Erreur : aucun client porte se nom.");
        }
    }

    public static void display(){
        for (String username : map.keySet()) {
            System.out.println(username);
        }
    }

    static public void newCustomer() throws IOException, NoSuchAlgorithmException{
        String username = userInput("Choisissez un nom d'utilisateur : ");
        if(map.containsKey(username)){
            System.out.println("Nom d'utilisateur déjà pris");
            return;
        }
        String password = userInput("Choisissez un mot de passe : ");
        add(username, password);
    }
    
    static public void connect() throws NumberFormatException, NoSuchAlgorithmException, IOException{
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
    * Enregistre les venderus dans un fichier .txt
    */
    public static void writeSave(){

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            for(HashMap.Entry<String,Customer> entry : map.entrySet()){
                bufferedWriter.write(entry.getValue().toString());
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
                map.put(str[0], new Customer(str[0],str[1],false));
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
