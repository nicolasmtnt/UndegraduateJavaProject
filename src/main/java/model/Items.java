package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.UUID;


public class Items{

    public static HashMap<UUID,Item> items = new HashMap<>();

    public static void add(String manufacturer, String name){
        items.put(UUID.randomUUID(), new Item(manufacturer,name));
        System.out.println(name = " ajouté(e)");
    }

    public static void display(){
        for(Item element : items.values()){
            System.out.println(" Brand : "+element.manufacturer + " Product : "+ element.name);
        }
    }

    public static UUID getUUID(String manufacturer, String name){
        UUID uuid = null;
        for(HashMap.Entry<UUID,Item> entry : items.entrySet()){
            if((manufacturer == entry.getValue().manufacturer) && (name == entry.getValue().name)){
                uuid = entry.getKey();
            }
        }
        return uuid;
    }

    public static void save(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/ressources/items.csv"));
            for (HashMap.Entry<UUID,Item> entry : items.entrySet()) {
                bw.write(entry.getKey().toString());
                bw.write(",");
                bw.write(entry.getValue().manufacturer+ ","+entry.getValue().name);
                bw.newLine();
            }
            bw.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void load(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/main/ressources/items.csv"));
            String str;
            while ((str = br.readLine()) != null) {
                String[] tokens = str.split(",");
                items.put(UUID.fromString(tokens[0]),new Item(tokens[1],tokens[2]));
            }
            br.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}