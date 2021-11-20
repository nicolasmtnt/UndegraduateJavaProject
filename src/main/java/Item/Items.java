package Item;

import java.util.HashMap;
import java.util.UUID;

public class Items{

    public static HashMap<UUID,Item> map = new HashMap<>(); // La Map qui contient des item et leurs UUID comme clé

    /**
     * Ajoute un produit en ajoutant à map une valeur de clé UUID (généré automatiquement) et un objet item
     * @param type : Prend une valeur parmit : movie, videogame, album
     * @param attributs : Les attributs de l'item que l'on veut ajouter à map(le nombre varie en fonction de si item est un album, un film, un jeu-video ...)
     */
    public static void add(String type, String[] attributs){
        switch (type) {
            case "album":
                map.put(UUID.randomUUID(), new Album(attributs[0], attributs[1], attributs[2], attributs[3]));
                System.out.println("Produit ajouté !");
                break;
        
            case "videogame" :
                map.put(UUID.randomUUID(), new VideoGame(attributs[0], attributs[1], attributs[2], attributs[3]));
                System.out.println("Produit ajouté !");
                break;

            case "movie":
                map.put(UUID.randomUUID(), new Movie(attributs[0], attributs[1], attributs[2], attributs[3]));
                System.out.println("Produit ajouté !");
                break;
        
            default:
                System.out.println("Operation failed : The '"+type +"' type of item is not defined by the system");
                break;
        }
    }

    /**
     * Affiche tous les produits (item) de map (HashMap)
     */
    public static void display(){
        System.out.println("Liste des Produits enregistrés :");
        for(Item element : map.values()){
            System.out.println(element.toString());
        }
        System.out.println("");
    }

    /**
     * Recherche un produit enregistré en parcourant map à la recherche d'un titre et d'une année correspondant
     * Not case sensitive
     * @param type Prend une valeur parmit : movie, videogame, album
     * @param title
     * @param year
     * @return uuid
     */
    public static UUID getUUID(String type, String title, String year){
        UUID uuid = null;
        for (HashMap.Entry<UUID,Item> element : map.entrySet()) {
            if(element.getValue().getClassName().equalsIgnoreCase(type) && element.getValue().getTitle().equalsIgnoreCase(title) && element.getValue().getYear().equalsIgnoreCase(year)){
                uuid =  element.getKey();
                break;
            }
        }
        return uuid;
    }

    /**
     * String donnant les informations sur l'article (Type, titre, date ...)
     * @param uuid
     * @return
     */
    public static Item getValue(UUID uuid){
        return map.get(uuid);
    }

    
}






// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.lang.ProcessBuilder.Redirect.Type;
// import java.util.Collection;
//     /**
//      *  Items --> .csv
//      */
//     public static void save(){
//         try{
//             BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/ressources/map.csv"));
//             bw.write("UUID, brand, product\n");
//             for (HashMap.Entry<UUID,Item> entry : map.entrySet()) {
//                 bw.write(entry.getKey().toString());
//                 bw.write(",");
//                 bw.write(entry.getValue().brand+ ","+entry.getValue().product);
//                 bw.newLine();
//             }
//             bw.close();
//         } catch(Exception e) {
//             e.printStackTrace();
//         }
//     }

//     /**
//      * .csv --> Items
//      */
//     public static void load(){
//         try{
//             BufferedReader br = new BufferedReader(new FileReader("src/main/ressources/map.csv"));
//             String str = br.readLine();
//             while ((str = br.readLine()) != null) {
//                 String[] tokens = str.split(",");
//                 map.put(UUID.fromString(tokens[0]),new Item(tokens[1],tokens[2]));
//             }
//             br.close();
//         } catch(Exception e){
//             e.printStackTrace();
//         }
//     }

//     public static void importSample(){

//     }