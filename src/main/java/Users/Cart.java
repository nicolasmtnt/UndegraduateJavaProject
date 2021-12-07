package Users;
import java.util.HashMap;
import java.util.UUID;


public class Cart{
    
    private HashMap<UUID,Integer> shoppingList = new HashMap<>();



//     public ArrayList<Item> getPanier(){
//         return this.listeProduit;
//     }

    public void addProduct(UUID uuid, Integer quantity){
        if(shoppingList.containsKey(uuid)){
            shoppingList.put(uuid, shoppingList.get(uuid) + quantity);
        }
        shoppingList.put(uuid, quantity);
    }

    public void removeProduct(UUID uuid){
        shoppingList.remove(uuid);
    }
}