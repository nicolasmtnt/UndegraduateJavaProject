package Users;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import Item.Item;


public class Panier{
  public HashMap<UUID,Integer> listeProduit;

  public Panier(){
    this.listeProduit = new HashMap<>();
  }

//   public ArrayList<Item> getPanier(){
//     return this.listeProduit;
//   }

  public void ajouteProduit(UUID idProd, Integer quantite){

  }
}