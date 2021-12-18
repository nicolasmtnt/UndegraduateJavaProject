
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import Gestionnary.*;
import Item.Items;
import Users.Customers;
import Users.Staff;


public class App{
    public static void main(String[] args) throws IOException, NumberFormatException, NoSuchAlgorithmException{
        // Création d'élements : 
        // Items.add("album", new String[]{"Dark Side of the Moon", "1988", "Pink Floyd", "Universal"});
        // Items.add("videogame", new String[]{"Super Mario bros","2019","Nintendo","Nintendo"});
        // Items.add("movie", new String[]{"Star Wars","2010","George Lucas","Lucasfilm"});
        // Items.add("movie", new String[]{"Star Wars","2010","George Lucas","Lucasfilm"}); // ajouter truc pour empecher doublon
        // Items.display();

        // Stock.add("movie", "Star Wars", "2010", 50);
        // Stock.add("videogame", "Super Mario bros", "2019", 12);
        
        // Stock.display();

        // Stock.toMarketplace("movie", "Star Wars", "2010", 20, 19.99);
        // Stock.toMarketplace("videogame", "Super Mario bros", "2019", 2, 19.99);

        // Stock.display();

        //remettre map en private après... ainsi que le constructeur de manager

        execute();
        
    }

    static void execute() throws IOException, NoSuchAlgorithmException, NumberFormatException{
        System.out.println(" *********** LANCEMENT DU PROGRAMME ***********\n");
        
        System.out.println("Chargement de la sauvergarde ...\n\n");
        Staff.readSave();
        Customers.readSave();
        Items.readSave();
        Stock.readSave();
        Marketplace.readSave();
        
        System.out.println("Entrez 'help' pour afficher les commandes.\n\n"+
        "Admin déjà présent : \n"+
        "nom d'utilisateur = Alice\n"+
        "mot de passe = admin \n");
        Boolean running = true;
        String in;
        while(running){
            newCommand();
            in = userInput();
            switch (in) {
                case "new":
                    Customers.newCustomer(); 
                    break;
                case "connect": 
                    Customers.connect();
                    break;
                case "admin": 
                    Staff.interact();
                    break;
                case "exit": 
                    running = false;
                    System.out.println("Sauvergarde ...");;
                    break;
                default:
                    System.out.println("Commande non trouvée");
                    break;
            }
        }
        Staff.writeSave();
        Customers.writeSave();
        Items.writeSave();
        Stock.writeSave();
        Marketplace.writeSave();

        System.out.println(" ************ FIN DU PROGRAMME ************\n");
    }
    
    static void newCommand(){
        System.out.print("\nnew : Créer un compte client\n"+
        "connect : se connecter en tant que client\n"+
        "admin : se connecter en tant que gérant du magazin\n"+
        "exit : Sauvegarder et quitter\n\n"+
        "Nouvelle commande : ");
    };

    static String userInput() throws IOException{
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    static String userInput(String message) throws IOException{
        System.out.println(message);
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    
}


/*
import java.io.*;
import java.util.*;

public class shell
{
    public static void main(String[] args) throws java.io.IOException {
        
        String commandLine;
        BufferedReader console = new BufferedReader
            (new InputStreamReader(System.in));
        
        while (true) {
            // read what the user entered
            System.out.print("My shell>");
            commandLine = console.readLine(); {
                // if the user entered a return, just loop again
                if (commandLine.equals("")) {
                    continue;
                }
                else if (commandLine.equalsIgnoreCase("exit")) {
                    System.out.println("Goodbye");
                    System.exit(0);
                }
                
                // split the string into a string array
                
                ArrayList<String> parms = new ArrayList<String>();
                String[] lineSplit = commandLine.split(" ");
                
                int size = lineSplit.length;
                for (int i=0; i<size; i++) {
                    parms.add(lineSplit[i]);
                }
                
                ProcessBuilder pb = new ProcessBuilder(parms);
                Process proc = pb.start();
                
                // obtain the input stream
                InputStream is = proc.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                
                // read what is returned by the command
                String line;
                while ((line = br.readLine()) != null)
                    System.out.println(line);
                    
                br.close();
                
            }
        }
    }
}

*/