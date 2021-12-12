package Gestionnary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import Item.Items;


public class Marketplace{
    static HashMap<String,Shelf> map = new HashMap<>();

    public static void displayManager(){
        System.out.println("Liste des élements en ventes:");
        for (HashMap.Entry<String,Shelf> entry : map.entrySet()) {
            System.out.println("\n    "+entry.getKey() + " :");
            entry.getValue().displayManager();

        }
    }

    public static void displayClient(){
        System.out.println("Liste des élements en ventes:");
        for (HashMap.Entry<String,Shelf> entry : map.entrySet()) {
            System.out.println("\n    "+entry.getKey() + " :");
            entry.getValue().displayClient();

        }
    }

    static Shelf get(String category){
        return map.get(category);
    }

    static void put(String category, Shelf shelf){
        map.put(category, shelf);
    }

    static public void add(String type, String title, String year, int quantity, double price){
        UUID uuid = Items.getUUID(type, title, year);
        add(uuid, quantity, price);
    }

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
            System.out.println("Produit mis en vente avec succès");
        } else{
            System.out.println("Opération impossible : le nombre d'article en stock n'est pas suffisant");
        }
    }

    static public void remove(String type, String title, String year,int quantity){
        UUID uuid = Items.getUUID(type, title, year);
        Marketplace.remove(uuid, quantity);
    }

    static public void remove(UUID uuid, int quantity){
        int count = 0;
        for(HashMap.Entry<String,Shelf> entry : map.entrySet()){
            count = entry.getValue().substract(uuid, quantity);
            if(entry.getValue().size()<1){
                map.remove(entry.getKey());
            }
            if(count>0){
                System.out.println("Élement retiré de la vente avec succès");
                break;
            }
        }
    }

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
}
