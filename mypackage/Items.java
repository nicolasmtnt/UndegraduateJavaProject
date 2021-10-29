package mypackage;
import java.util.HashMap;
import java.util.UUID;

public class Items{
    public static HashMap<UUID,Item> items = new HashMap<>();

    public static void add(String manufacturer, String name){
        items.put(UUID.randomUUID(), new Item(manufacturer,name));
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

}