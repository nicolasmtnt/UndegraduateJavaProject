package Gestionnary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import Interface.Scannable;
import Item.Items;

public class Stock{

    static HashMap<UUID,Integer> map = new HashMap<>();

    /**
     * Ajoute un article au stock
     * @param type Prend une valeur parmit : movie, videogame, album
     * @param title
     * @param year
     * @param quantity
     */
    public static void add(String type, String title, String year, int quantity){
        UUID uuid = Items.getUUID(type, title, year);
        try{
            map.put(uuid, map.get(uuid)+quantity);
        } catch (NullPointerException e){
            map.put(uuid, quantity);
        }
    }

    public static Integer getValue(UUID uuid){
        return map.get(uuid);
    }

    public static Integer changeValue(UUID uuid, Integer x){
        return map.put(uuid, x);
    }

    public static void add(UUID uuid, int quantity){
        try{
            map.put(uuid, map.get(uuid)+quantity);
        } catch (NullPointerException e){
            map.put(uuid, quantity);
        }
        System.out.println("Produit ajouté au stock");
    }

    /**
     * Affiche l'ensemble des articles en stock
     */
    public static void display(){
        System.out.println("Liste des éléments en stock :");
        for(HashMap.Entry<UUID, Integer> entry : map.entrySet()){
            System.out.println(Items.getValue(entry.getKey()) + " , quantité : " + entry.getValue());
        }
        System.out.println("");
    }

    /**
     * Retirer un article du stock
     * @param type Prend une valeur parmit : movie, videogame, album
     * @param title
     * @param year
     * @param quantity
     */
    public static void substract(String type, String title, String year, int quantity){
        UUID uuid = Items.getUUID(type, title, year);
        try{
            map.put(uuid, map.get(uuid)-quantity);
            if (map.get(uuid)<1){
                map.remove(uuid);
            }
        } catch (NullPointerException e){
            System.out.println("Cette article n'est pas dans la liste");
        }
    }

    public static void substract(UUID uuid, int quantity){
        try{
            map.put(uuid, map.get(uuid)-quantity);
            if (map.get(uuid)<1){
                map.remove(uuid);
            }
        } catch (NullPointerException e){
            System.out.println("Cette article n'est pas dans la liste");
        }
    }

    //   Méthodes de transfert


    static public void toMarketplace(String category, String title, String year, int quantity, double price){
        UUID uuid = Items.getUUID(category, title, year);
        try {
            Marketplace.get(category).add(uuid, quantity, price);
            substract(category, title, year, quantity);
        } catch (NullPointerException e) {
            Marketplace.put(category, new Shelf(category, uuid, quantity, price));
            substract(category, title, year, quantity);
        }
    }



    public static ArrayList<UUID> search(String str){
        ArrayList<UUID> foundItems = new ArrayList<>();
        for(HashMap.Entry<UUID,Integer> element : map.entrySet()){
            if(Scannable.staticScan(Items.getValue(element.getKey()),str)){
                foundItems.add(element.getKey());
                System.out.println("["+foundItems.size()+"] "+Items.getValue(element.getKey()).toString()+ ", quantité ("+element.getValue().toString()+")");
            }
        }
        return foundItems;
    }

}
