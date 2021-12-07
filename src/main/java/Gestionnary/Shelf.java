package Gestionnary;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import Interface.Scannable;
import Item.Item;
import Item.Items;

public class Shelf implements Scannable{
    HashMap<UUID,Number[]> map;
    String category;
    

    public Shelf(String category, UUID uuid, int quantity, double price) {
        this.category = category;
        this.map = new HashMap<>();
        map.put(uuid, new Number[]{quantity, price});
    }
    /**
     * Ajoute un élement à la vente
     * Doit normalement seulement être utilisé par la fonction toStock() et toMarketplace() pour que l'article ne viennent pas de nulle part
     * @param category Prend une valeur parmit : movie, videogame, album
     * @param title
     * @param year
     * @param quantity
     * @param price // donnée spécifique aux articles à vendre
     */
    public void add(UUID uuid, int quantity, double price){
        try{
            if((double)map.get(uuid)[1] != price){
                // Voulez vous changez le prix de vente de l'article (y/n) ?
            }
            map.put(uuid, new Number[]{(int)map.get(uuid)[0]+quantity, (double)price});
        } catch (NullPointerException e){
            map.put(uuid, new Number[]{(int)quantity, (double)price});
        }
    }
    /**
     * Affiche l'ensemble des articles en ventes, leurs prix et la quantité en magazin
     */
    public void display(){
        for(HashMap.Entry<UUID, Number[]> entry : map.entrySet()){
            System.out.println(Items.getValue(entry.getKey()) + ", quantité : " + entry.getValue()[0] + " , prix : " + entry.getValue()[1]+"€");
        }
    }

    /**
     * Retire un element de la vente
     * Doit normalement seulement être utilisé par la fonction toStock() et marketplace()
     * @param category Prend une valeur parmit : movie, videogame, album
     * @param title
     * @param year
     * @param quantity
     */
    public void substract(String title, String year, int quantity){
        UUID uuid = Items.getUUID(category, title, year);
        try{
            map.put(uuid, new Number[]{(int)map.get(uuid)[0]-quantity, map.get(uuid)[1]});
            if ((int)map.get(uuid)[0]<1){
                map.remove(uuid);
            }
        } catch (NullPointerException e){
            System.out.println("Cette article n'est pas dans la liste");
        }
    }

    public int substract(UUID uuid, int quantity){
        try{
            map.put(uuid, new Number[]{(int)map.get(uuid)[0]-quantity, map.get(uuid)[1]});
            if ((int)map.get(uuid)[0]<1){
                map.remove(uuid);
                return 1;
            }
        } catch (NullPointerException e){
            return 0;
        }
        return 0;
    }

    

    //   Méthodes de transfert

    /**
     * Renvoie un article en vente vers le stock
     * @param category Prend une valeur parmit : movie, videogame, album
     * @param title
     * @param year
     * @param quantity
     */

    public void toStock(String title, String year, int quantity){
        UUID uuid = Items.getUUID(category, title, year);
        try {
            Stock.add(category, title, year, Math.min((int)map.get(uuid)[0],quantity));
            substract(title, year, quantity);
            
        } catch (Exception e) {
            System.out.println("Cette article n'est pas den rayon");
        }
    }

    public Set<UUID> keySet(){
        return map.keySet();
    }

    public ArrayList<UUID> search(String str, int count){
        ArrayList<UUID> foundItems = new ArrayList<>();
        for(UUID uuid : this.map.keySet()){
            Item item = Items.getValue(uuid);
            if(scan(item, str)){
                foundItems.add(uuid);
                System.out.println("["+sum(foundItems.size(),count) +"] "+item.toString()+ ", quantité("+ this.map.get(uuid)[0]+"), prix("+ this.map.get(uuid)[1]+")");
            }
        }
        return foundItems;
    }

    private int sum(int a, int b){
        return a+b;
    }

}
