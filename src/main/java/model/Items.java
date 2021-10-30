package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.UUID;


public class Items{

    public static HashMap<UUID,Item> items = new HashMap<>(); // La Map qui contient des item et leurs UUID comme clé

    /**
     * Ajoute un élément à items (l'UUID est généré automatiquement)
     * @param brand
     * @param product
     */
    public static void add(String brand, String product){
        items.put(UUID.randomUUID(), new Item(brand,product));
        System.out.println(product = " ajouté(e)");
    }

    /**
     * Affiche tous les Item élements de Items
     */
    public static void display(){
        for(Item element : items.values()){
            System.out.println(" Brand: "+element.brand + " | Product: "+ element.product);
        }
    }

    /**
     * Cherche un Item de Items et renvoie son UUID (Key)
     * @param brand
     * @param product
     * @return
     */
    public static UUID getUUID(String brand, String product){
        UUID uuid = null;
        for(HashMap.Entry<UUID,Item> entry : items.entrySet()){
            if((brand == entry.getValue().brand) && (product == entry.getValue().product)){
                uuid = entry.getKey();
            }
        }
        return uuid;
    }

    /**
     *  Items --> .csv
     */
    public static void save(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/ressources/items.csv"));
            bw.write("UUID, brand, product\n");
            for (HashMap.Entry<UUID,Item> entry : items.entrySet()) {
                bw.write(entry.getKey().toString());
                bw.write(",");
                bw.write(entry.getValue().brand+ ","+entry.getValue().product);
                bw.newLine();
            }
            bw.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * .csv --> Items
     */
    public static void load(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/main/ressources/items.csv"));
            String str = br.readLine();
            while ((str = br.readLine()) != null) {
                String[] tokens = str.split(",");
                items.put(UUID.fromString(tokens[0]),new Item(tokens[1],tokens[2]));
            }
            br.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void importSample(){

    }

}