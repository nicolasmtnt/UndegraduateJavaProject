
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Field;

import Gestionnary.*;
import Item.Items;


public class App{
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException{

        System.out.println("\n ************ DEBUT DU PROGRAMME ************");

        // Création d'élements : 
        Items.add("album", new String[]{"Dark Side of the Moon", "1988", "Pink Floyd", "Universal"});
        Items.add("videogame", new String[]{"Super Mario bros","2019","Nintendo","Nintendo"});
        Items.add("movie", new String[]{"Star Wars","2010","George Lucas","Lucasfilm"});
        Items.add("movie", new String[]{"Star Wars","2010","George Lucas","Lucasfilm"}); // ajouter truc pour empecher doublon
        Items.display();

        Stock.add("movie", "Star Wars", "2010", 50);
        
        Stock.display();

        Stock.toMarketplace("movie", "Star Wars", "2010", 20, 19.99);
        Stock.toMarketplace("movie", "Star Wars", "2010", 2, 19.99);

        Marketplace.display();
        Stock.display();
        
        System.out.println(" ************ FIN DU PROGRAMME ************\n");

        System.out.println(Items.getValue(Items.getUUID("movie", "Star Wars","2010")).toString());

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