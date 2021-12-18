package Item;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.Scanner;

import Interface.Scannable;

public class Items{

    public static HashMap<UUID,Item> map = new HashMap<>(); // La Map qui contient des item et leurs UUID comme clé
    public static String filePath = "src/main/ressources/Items.txt";

    public static void add(String type, String[] attributs){
        UUID uuid = UUID.randomUUID();
        File file = new File("src/main/ressources/history.csv");
        try (BufferedWriter fo = new BufferedWriter(new FileWriter(file,true))) {
            String message;
            switch (type) {
                case "album":
                    map.put(uuid, new Album(attributs[0], attributs[1], attributs[2], attributs[3]));

                    message = uuid.toString() + "," + attributs[0] + "," +  attributs[1] + "," + attributs[2] + "," + attributs[3]+"\n";
                    fo.write(message);
                    fo.close();

                    System.out.println("Produit ajouté !");
                    break;

                case "videogame" :
                    map.put(uuid, new VideoGame(attributs[0], attributs[1], attributs[2], attributs[3]));
                    message = uuid.toString() + "," + attributs[0] + "," + attributs[1] + "," + attributs[2] + "," + attributs[3]+"\n";
                    fo.write(message);
                    fo.close();

                    System.out.println("Produit ajouté !");
                    break;

                case "movie":
                    map.put(uuid, new Movie(attributs[0], attributs[1], attributs[2]));
                    message = uuid.toString() + "," + attributs[0] + "," + attributs[1] + "," + attributs[2]+"\n";
                    fo.write(message);
                    fo.close();

                    System.out.println("Produit ajouté !");
                    break;

                default:
                    System.out.println("Operation failed : The '"+type +"' type of item is not defined by the system");
                    break;
            }
        } catch (IOException e) {
            System.out.println("An error occured");
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

    public static String getSimpleName(UUID uuid){
        Item item = map.get(uuid);
        return item.getTitle()+" ("+item.year+")";
    }

    /**
     * Utilisé pour afficher à l'utilisateur la liste des articles correspondant à sa recherche
     * @param str
     * @return ArrayList (indexé de 1 à n) des n articles correspondants au Regex de str (Not case-sensitive)
     */
    public static ArrayList<UUID> search(String str){
        ArrayList<UUID> foundItems = new ArrayList<>();
        for(HashMap.Entry<UUID,Item> element : map.entrySet()){
            if(Scannable.staticScan(element.getValue(),str)){
                foundItems.add(element.getKey());
                System.out.println("["+foundItems.size()+"] "+element.getValue().toString());
            }
        }
        return foundItems;
    }

    /**
     * Transform le contenu du fichier database en liste afin de pouvoir y traiter les doublons. Pour l'instant, ne retourne rien
     et print la liste pour tester, mais à terme la methode devra retouner la liste afin de la transmettre à une methode de check doublons
     */
    @Deprecated
    public void transformList() throws IOException {
    try {
      Scanner s = new Scanner(new File("src/main/ressources/database.csv"));
      ArrayList<String> list = new ArrayList<String>();
      while (s.hasNext()){
        list.add(s.next());
      }
      s.close();

      System.out.println(list);
    } catch (IOException e) {
      System.out.println("Lecture du fichier impossible");
    }
  }

  /**
   * Enregistre les articles dans un fichier .txt
   */
  public static void writeSave(){

    try {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        for(HashMap.Entry<UUID,Item> entry : map.entrySet()){
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
            if(str.length>1){
                switch(str[1]){
                    case "VideoGame":
                        map.put(UUID.fromString(str[0]),new VideoGame(str[2],str[3],str[4],str[5]));
                        break;
                    case "Album":
                        map.put(UUID.fromString(str[0]),new Album(str[2],str[3],str[4],str[5]));
                        break;
                    case "Movie":
                        map.put(UUID.fromString(str[0]),new Movie(str[2],str[3],str[4]));
                        break;
                    default:
                        break;
                }
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println(e);
    }
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
