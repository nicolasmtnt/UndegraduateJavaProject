package Gestionnary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

import Interface.Scannable;
import Item.Items;

public class Stock{

    static HashMap<UUID,Integer> map = new HashMap<>();
    public static String filePath = "src/main/ressources/Stock.txt";


    /**
     * Ajoute un article au stock
     * @param type Prend une valeur parmit : movie, videogame, album
     * @param title
     * @param year
     * @param quantity
     */
    public static void add(String type, String title, String year, int quantity){
        UUID uuid = Items.getUUID(type, title, year);
        add(uuid, quantity);
        System.out.println("Article ajouté");

    }

    public static Integer getValue(UUID uuid){
        return map.get(uuid);
    }

    public static Integer changeValue(UUID uuid, Integer x){
        return map.put(uuid, x);
    }

    public static void add(UUID uuid, int quantity){
        File file = new File("src/main/ressources/historyStock.csv");
        try (BufferedWriter fo = new BufferedWriter(new FileWriter(file,true))) {
            try{
                String message = uuid.toString() + "," + quantity+"\n";
                fo.write(message);
                fo.close();

                map.put(uuid, map.get(uuid)+quantity);
            } catch (NullPointerException e){
                map.put(uuid, quantity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche l'ensemble des articles en stock
     */
    public static void display(){
        System.out.println("\nListe des éléments en stock :");
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
    public static void remove(String type, String title, String year, int quantity){
        UUID uuid = Items.getUUID(type, title, year);
        remove(uuid, quantity);
    }
    
    public static void remove(UUID uuid, int quantity){
        try{
            map.put(uuid, map.get(uuid)-quantity);
            if (map.get(uuid)<1){
                map.remove(uuid);
            }
        } catch (NullPointerException e){
            System.out.println("Une erreur c'est produite :Cette article n'est pas dans la liste");
        }
    }

    //   Méthodes de transfert


    static public void toMarketplace(String category, String title, String year, int quantity, double price){
        UUID uuid = Items.getUUID(category, title, year);
        try {
            Marketplace.get(category).add(uuid, quantity, price);
            remove(category, title, year, quantity);
        } catch (NullPointerException e) {
            Marketplace.put(category, new Shelf(category, uuid, quantity, price));
            remove(category, title, year, quantity);
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

    /**
    * Enregistre les venderus dans un fichier .txt
    */
    public static void writeSave(){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            for(HashMap.Entry<UUID,Integer> entry : map.entrySet()){
                bufferedWriter.write(entry.getKey().toString()+","+entry.getValue().toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            } catch (IOException e) {
            System.out.println(e);
            }
        }

    /**
    * Récupère les articles enregistré dans un .text durant la dernière session
    */
    public static void readSave(){
        Scanner scanner;
        try{
            scanner = new Scanner(new FileInputStream(filePath));
            while(scanner.hasNext()){
                String[] str = scanner.nextLine().split(",");
                map.put(UUID.fromString(str[0]), Integer.parseInt(str[1]));
            } 
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

}
