package Gestionnary;

import java.util.HashMap;
import java.util.UUID;

import Item.Items;

public class Rayon{

    static HashMap<UUID,Number[]> map = new HashMap<>();

    /**
     * Ajoute un élement à la vente
     * Doit normalement seulement être utilisé par la fonction toStock() et toMarketplace() pour que l'article ne viennent pas de nulle part
     * @param type Prend une valeur parmit : movie, videogame, album
     * @param title
     * @param year
     * @param quantity
     * @param price // donnée spécifique aux articles à vendre
     */
    public static void add(String type, String title, String year, int quantity, double price){
        UUID uuid = Items.getUUID(type, title, year);
        try{
            if((double)map.get(uuid)[1] != price){
                // Voulez vous changez le prix de vente de l'article (y/n) ?
            }
            map.put(uuid, new Number[]{(int)map.get(uuid)[1]+quantity,price});
        } catch (NullPointerException e){
            map.put(uuid, new Number[]{(int)quantity, (double)price});
        }
    }
    /**
     * Affiche l'ensemble des articles en ventes, leurs prix et la quantité en magazin
     */
    public static void display(){
        for(HashMap.Entry<UUID, Number[]> entry : map.entrySet()){
            System.out.println(Items.getValue(entry.getKey()) + " , quantité : " + entry.getValue()[0] + " , prix : " + entry.getValue()[1]+"€");
        }
    }

    /**
     * Retire un element de la vente
     * Doit normalement seulement être utilisé par la fonction toStock() et marketplace()
     * @param type Prend une valeur parmit : movie, videogame, album
     * @param title
     * @param year
     * @param quantity
     */
    public static void substract(String type, String title, String year, int quantity){
        UUID uuid = Items.getUUID(type, title, year);
        try{
            map.put(uuid, new Number[]{(int)map.get(uuid)[0]-quantity, map.get(uuid)[1]});
            if ((int)map.get(uuid)[0]<1){
                map.remove(uuid);
            }
        } catch (NullPointerException e){
            System.out.println("Cette article n'est pas dans la liste");
        }
    }

    //   Méthodes de transfert

    /**
     * Renvoie un article en vente vers le stock
     * @param type Prend une valeur parmit : movie, videogame, album
     * @param title
     * @param year
     * @param quantity
     */
    public static void toStock(String type, String title, String year, int quantity){
        UUID uuid = Items.getUUID(type, title, year);
        Stock.add(type, title, year, Math.min((int)map.get(uuid)[0],quantity));
        substract( type,  title,  year,  quantity);
    }
}
