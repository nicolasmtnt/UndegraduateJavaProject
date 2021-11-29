package Users;
import java.util.HashMap;
import java.util.UUID;

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
