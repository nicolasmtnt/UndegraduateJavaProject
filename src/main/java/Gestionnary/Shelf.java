package Gestionnary;

import java.io.BufferedWriter;
import java.io.IOException;
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
    public void displayManager(){
        for(HashMap.Entry<UUID, Number[]> entry : map.entrySet()){
            System.out.println(Items.getValue(entry.getKey()) + ", quantité : " + entry.getValue()[0] + " , prix : " + entry.getValue()[1]+"€");
        }
    }

    public void displayClient(){
        for(HashMap.Entry<UUID, Number[]> entry : map.entrySet()){
            if(entry.getValue()[0].intValue() < 5){
                System.out.println(Items.getValue(entry.getKey()) + " , prix : " + entry.getValue()[1]+"€"+" (presque épuisé)");
            } else {
                System.out.println(Items.getValue(entry.getKey()) + " , prix : " + entry.getValue()[1]+"€");
            }
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
            Stock.add(uuid, quantity);
        } catch (NullPointerException e){
            System.out.println("Cette article n'est pas dans la liste");
        }
    }

    public int substract(UUID uuid, int quantity){
        try{
            map.put(uuid, new Number[]{(int)map.get(uuid)[0]-quantity, map.get(uuid)[1]});
            if ((int)map.get(uuid)[0]<1){
                map.remove(uuid);
                Stock.add(uuid, quantity);
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

    /**
     * Méthode utilisé par la méthode search() de Marketplace et donc par l'interface Searchable
     * @param str
     * @param count
     * @return
     */
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

    public Double getPrice(UUID uuid){
        for (HashMap.Entry<UUID,Number[]> entry : map.entrySet()){
            if(entry.getKey().equals(uuid)){
                return (Double)entry.getValue()[1];
            }
        }
        return null;
    }

    public Integer getQuantity(UUID uuid){
        for (HashMap.Entry<UUID,Number[]> entry : map.entrySet()){
            if(entry.getKey().equals(uuid)){
                return (Integer)entry.getValue()[0];
            }
        }
        return null;
    }

    private int sum(int a, int b){
        return a+b;
    }

    int size(){
        return map.size();
    }

    /**
     * Méthode utilisé par la méthode writeSave() de Marketplace
     * @param bufferedWriter
     */
    public void writeSave(BufferedWriter bufferedWriter){
        try {
            for(HashMap.Entry<UUID,Number[]> entry : this.map.entrySet()){
                bufferedWriter.write(entry.getKey().toString()+","+entry.getValue()[0].toString()+","+entry.getValue()[1].toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
