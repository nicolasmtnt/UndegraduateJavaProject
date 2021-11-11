package Gestionnary;

import java.util.HashMap;
import java.util.UUID;

import Item.Items;

public class Stock {

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

    /**
     * Affiche l'ensemble des articles en stock
     */
    public static void display(){
        for(HashMap.Entry<UUID, Integer> entry : map.entrySet()){
            System.out.println(Items.getValue(entry.getKey()) + " , quantité : " + entry.getValue());
        }
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

    //   Méthodes de transfert

    /**
     * Mets en rayon un article qui est en stock
     * @param type Prend une valeur parmit : movie, videogame, album
     * @param title
     * @param year
     * @param quantity
     * @param price
     */
    public static void toMarketplace(String type, String title, String year, int quantity, double price){
        UUID uuid = Items.getUUID(type, title, year);
        Marketplace.add(type, title, year, Math.min(quantity, map.get(uuid)), price);
        substract(type, title, year, quantity);

    }

}
