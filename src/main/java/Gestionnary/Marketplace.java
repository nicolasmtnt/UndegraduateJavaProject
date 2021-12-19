package Gestionnary;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;
import Item.Items;


public class Marketplace{
    static HashMap<String,Shelf> map = new HashMap<>();
    public static String filePath = "src/main/ressources/Marketplace.txt";

    // display adapté au gérant
    public static void displayManager(){
        System.out.println("Liste des élements en ventes:");
        for (HashMap.Entry<String,Shelf> entry : map.entrySet()) {
            System.out.println("\n    "+entry.getKey() + " :");
            entry.getValue().displayManager();

        }
    }

    // display adapté au client (ne donne pas le nombre d'article en stock ("presque épuisé" si il n'y en reste plus beaucoup)
    public static void displayClient(){
        System.out.println("Liste des élements en ventes:");
        for (HashMap.Entry<String,Shelf> entry : map.entrySet()) {
            System.out.println("\n    "+entry.getKey() + " : ");
            entry.getValue().displayClient();

        }
    }

    static Shelf get(String category){
        return map.get(category);
    }

    static void put(String category, Shelf shelf){
        map.put(category, shelf);
    }

    /**
     * Permet au vendeur de transférér un article de l'état de "en stock" à l'état "en vete"
     * Stock -> Marketplace (Shelf)
     * @param uuid
     * @param quantity
     * @param price
     */
    static public void add(UUID uuid, int quantity, double price){
        if(quantity <= Stock.getValue(uuid)){
            String category = Items.getValue(uuid).getClassName();
            try {
                map.get(category).add(uuid, quantity, price);
                Stock.remove(uuid, quantity);
            } catch (NullPointerException e) {
                map.put(category, new Shelf(category, uuid, quantity, price));
                Stock.remove(uuid, quantity);
            }
        } else{
            System.out.println("Opération impossible : le nombre d'article en stock n'est pas suffisant");
        }
    }

    /**
     * Comme add() mais adapté à la fonction readSave()
     */
    static public void addWrite(UUID uuid, int quantity, double price){
        if(quantity <= Stock.getValue(uuid)){
            String category = Items.getValue(uuid).getClassName();
            try {
                map.get(category).add(uuid, quantity, price);
            } catch (NullPointerException e) {
                map.put(category, new Shelf(category, uuid, quantity, price));
            }
        } else{
            System.out.println("Opération impossible : le nombre d'article en stock n'est pas suffisant");
        }
    }

    /**
     * Retire quantity élement de la vente
     * Si il n'y en a plus aucun, alors l'entrée dans le Shelf associé est supprimé
     * @param uuid
     * @param quantity
     */
    static public void remove(UUID uuid, int quantity){
        int count = 0;
        for(HashMap.Entry<String,Shelf> entry : map.entrySet()){
            count = entry.getValue().substract(uuid, quantity);
            if(entry.getValue().size()<1){
                map.remove(entry.getKey());
            }
            if(count>0){
                break;
            }
        }
    }

    /**
     * Comme remove(UUID uuid, int quantity) mais ici adapté à l'intérface client
     * @param uuid
     * @param quantity
     */
    static public void removeAfterBought(UUID uuid, int quantity){
        for(HashMap.Entry<String,Shelf> entry : map.entrySet()){
            entry.getValue().substract(uuid, quantity);
        }
        for(HashMap.Entry<String,Shelf> entry : map.entrySet()){
            if(entry.getValue().size()<1){
                map.remove(entry.getKey());
            }
        }
    }

    /**
     * Méthode utilisé par l'Interface Searchable pour effectuer une recherche
     * @param str
     * @return
     */
    public static ArrayList<UUID> search(String str){
        ArrayList<UUID> foundItems = new ArrayList<>();
        int count = 0;
        for(Shelf element: map.values()){
            ArrayList<UUID> foundUUID = element.search(str,count);
            foundItems.addAll(foundUUID);
            count+= foundItems.size();
        }
        return foundItems;
    }

    public static ArrayList<UUID> searchForClient(String str){
        ArrayList<UUID> foundItems = new ArrayList<>();
        int count = 0;
        for(Shelf element: map.values()){
            ArrayList<UUID> foundUUID = element.searchForClient(str,count);
            foundItems.addAll(foundUUID);
            count+= foundItems.size();
        }
        return foundItems;
    }

    public static Double getPrice(UUID uuid){
        for(Shelf element: map.values()){
            Double price = element.getPrice(uuid);
            if(price != null){
                return price;
            }
        }
        return 0.;

    }

    public static int getQuantity(UUID uuid){
        for(Shelf element: map.values()){
            Integer quantity = element.getQuantity(uuid);
            if(quantity != null){
                return quantity;
            }
        }
        return 0;
    }

    /**
    * Enregistre les articles en vente dans un fichier .txt
    */
    public static void writeSave(){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            for(HashMap.Entry<String,Shelf> entry : map.entrySet()){
                entry.getValue().writeSave(bufferedWriter);
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    /**
    * Récupère les articles en vente dans un .text durant la dernière session
    */
    public static void readSave(){
        Scanner scanner;
        try{
            scanner = new Scanner(new FileInputStream(filePath));
            while(scanner.hasNext()){
                String[] str = scanner.nextLine().split(",");
                addWrite(UUID.fromString(str[0]), Integer.parseInt(str[1]), Double.parseDouble(str[2]));
            } 
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }


}
