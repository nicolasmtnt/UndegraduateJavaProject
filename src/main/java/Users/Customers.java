package Users;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Customers{
    public static HashMap<String, Customer> map = new HashMap<>();

    public static void addCustomer(String username, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        map.put(username, new Customer(username, password));
    }

    // remove customer, être sur qu'il n'achète rien

    public static void display(){
        for (String username : map.keySet()) {
            System.out.println(username);
        }
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
