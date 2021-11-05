package Gestionnary;

import java.util.HashMap;
import java.util.UUID;

import Item.Items;

public abstract class Stock {

    static HashMap<UUID,Integer> map = new HashMap<>();

    /**
     * Ajoute un article au stock
     * @param type
     * @param title
     * @param year
     * @param x
     */
    public static void add(String type, String title, String year, int x){
        UUID uuid = Items.getUUID(type, title, year);
        try{
            map.put(uuid, map.get(uuid)+x);
        } catch (NullPointerException e){
            map.put(uuid, x);
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
     * @param type
     * @param title
     * @param year
     * @param x
     */
    public static void substract(String type, String title, String year, int x){
        UUID uuid = Items.getUUID(type, title, year);
        try{
            map.put(uuid, map.get(uuid)-x);
            if (map.get(uuid)<1){
                map.remove(uuid);
            }
        } catch (NullPointerException e){
            System.out.println("Cette article n'est pas dans la liste");
        }
    }

    //   Méthodes de transfert


    public static void toMarketplace(String type, String title, String year, int x, double price){
        UUID uuid = Items.getUUID(type, title, year);
        Marketplace.add(type, title, year, Math.min(x, map.get(uuid)), price);
        substract(type, title, year, x);

    }

}
