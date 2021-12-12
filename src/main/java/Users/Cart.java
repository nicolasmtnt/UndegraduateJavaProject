package Users;

import java.util.HashMap;
import java.util.UUID;

import Gestionnary.Marketplace;
import Item.Item;
import Item.Items;


public class Cart{
    
    private HashMap<UUID,Integer> shoppingList = new HashMap<>();



//     public ArrayList<Item> getPanier(){
//         return this.listeProduit;
//     }

    public void addProduct(UUID uuid, Integer quantity){
        if(quantity>0){
            if(shoppingList.containsKey(uuid)){
                shoppingList.put(uuid, shoppingList.get(uuid) + quantity);
            }
            shoppingList.put(uuid, quantity);
            System.out.println("Article ajouté au panier");
        }
    }

    public void removeProduct(UUID uuid){
        shoppingList.remove(uuid);
    }

    public void changeQuantity(UUID uuid, Integer quantity){
        if(quantity<1){
            shoppingList.remove(uuid);
        }else{
            shoppingList.put(uuid, quantity);
        }
    }


    public void display(){
        System.out.println("Articles dans le panier : ");
        int i = 0;
        for (HashMap.Entry<UUID,Integer> element : shoppingList.entrySet()) {
            i++;
            System.out.println("["+i+"] "+ Items.getValue(element.getKey()) +", "+element.getValue()) ;
        }
    }

    public void reset(){
        shoppingList.clear();
    }

    public void buy(){
        for (HashMap.Entry<UUID,Integer> entry : shoppingList.entrySet()) {
            Marketplace.removeAfterBought(entry.getKey(), entry.getValue());
        }
        reset();
    }

    public Double totalPrice(){
        Double price = 0.;
        for (HashMap.Entry<UUID,Integer> item : shoppingList.entrySet()) {
            price += Marketplace.getPrice(item.getKey())*item.getValue();
        }
        return price;
    }
}