import java.util.HashMap;
import java.util.UUID;

public class Panier{
  private HashMap<UUID,Integer> listeProduit;

  public Panier(){
    this.listeProduit = new HashMap<>();
  }

  public ArrayList<Produit> getPanier(){
    return this.listeProduit;
  }

  public void ajouteProduit(UUID idProd, Integer quantite){

  }
}
