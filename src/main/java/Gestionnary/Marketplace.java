package Gestionnary;

import java.util.HashMap;
import java.util.UUID;

import Item.Items;

public class Marketplace{
    static HashMap<String,Shelf> marketplace = new HashMap<>();

    public static void display(){
        System.out.println("Liste des élements en ventes:");
        for (HashMap.Entry<String,Shelf> entry : marketplace.entrySet()) {
            System.out.println(entry.getKey() + " :");
            entry.getValue().display();

        }
    }

    static Shelf get(String category){
        return marketplace.get(category);
    }

    static void put(String category, Shelf shelf){
        marketplace.put(category, shelf);
    }

    static public void add(UUID uuid, int quantity, double price){
        String category = Items.getValue(uuid).getClassName();
        try {
            marketplace.get(category).add(uuid, quantity, price);
        } catch (NullPointerException e) {
            marketplace.put(category, new Shelf(category, uuid, quantity, price));
        }
    }
}
