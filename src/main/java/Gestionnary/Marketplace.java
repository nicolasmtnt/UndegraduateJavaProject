package Gestionnary;

import java.util.HashMap;
import java.util.UUID;

import Item.Items;


public class Marketplace{
    static HashMap<String,Shelf> map = new HashMap<>();

    public static void display(){
        System.out.println("Liste des élements en ventes:");
        for (HashMap.Entry<String,Shelf> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " :");
            entry.getValue().display();

        }
    }

    static Shelf get(String category){
        return map.get(category);
    }

    static void put(String category, Shelf shelf){
        map.put(category, shelf);
    }

    static public void add(UUID uuid, int quantity, double price){
        if(quantity <= Stock.getValue(uuid)){
            String category = Items.getValue(uuid).getClassName();
            try {
                map.get(category).add(uuid, quantity, price);
            } catch (NullPointerException e) {
                map.put(category, new Shelf(category, uuid, quantity, price));
            }
            Stock.changeValue(uuid, Stock.getValue(uuid)-quantity);
            System.out.println("Produit mis en vente avec succès");
        } else{
            System.out.println("Opération impossible : le nombre d'article en stock n'est pas suffisant");
        }
    }
}
