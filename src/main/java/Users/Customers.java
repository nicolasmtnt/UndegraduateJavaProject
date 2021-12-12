package Users;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Customers{
    private static HashMap<String, Customer> map = new HashMap<>();

    public static void add(String username, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        map.put(username, new Customer(username, password));
        System.out.println("Compte crée");
    }

    public static void remove(String username) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if(map.remove(username) == null){
            System.out.println("Erreur : aucun client porte se nom.");
        }
    }

    // remove customer, être sur qu'il n'achète rien

    public static void display(){
        for (String username : map.keySet()) {
            System.out.println(username);
        }
    }

    static public void newCustomer() throws IOException, NoSuchAlgorithmException{
        String username = userInput("Choisissez un nom d'utilisateur");
        if(map.containsKey(username)){
            System.out.println("Nom d'utilisateur déjà pris");
            newCustomer();
        }
        String password = userInput("Choisissez un mot de passe");
        add(username, password);
    }
    
    static public void connect() throws NumberFormatException, NoSuchAlgorithmException, IOException{
        try {
            map.get(userInput("Entrez votre nom d'utilisateur :")).interact(userInput("Entrez votre mot de passe : "));
        } catch (NullPointerException e) {
            System.out.println("Le nom d'utilisateur entré est inconnue");
        }
    
    }

    static String userInput(String message) throws IOException{
        System.out.println(message);
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

}
// public class Client implements IUtilisateur{
//   public String pseudoUnique;
//   public String passWord;
//   public Panier panier;

//   public Client(String pseudoUnique, String passWord){
//     this.pseudoUnique = pseudoUnique;
//     this.passWord = passWord;
//     this.panier = new Panier;

//     //On enregistre le nouveau client dans un fihchier nous servant de Base de données
//     String PseudoMdp = this.pseudoUnique + " " + this.passWord; //Dans un fichier on stock pseudo et mdp; ex : "Babar soleil31"
//     BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/ressources/Users.txt"));
//     writer.write(PseudoMdp);
//     writer.close();
//   }

//   public HashMap<UUID,Integer> getPanier(){
//     return this.panier.getPanier();
//   }

//   public String toString(){
//     return "(CLIENT)  Pseudo : " + this.pseudoUnique + "mdp : " + this.passWord;
//   }


//   public ArrayList<Magasin>() voirListeMagasin(){

//   }

//   public void choisirMagasin(){

//   }

//   public ArrayList<Rayon>() voirListeRayon(){

//   }

//   public void choisirRayon(){

//   }

//   public ArrayList<Produit>() voirListeProduit(){

//   }

//   public void choisirProduit(){

//   }

//   public void acheterProduit(){

//   }
// }
