package Users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import Gestionnary.Marketplace;
import Interface.Interactable;
import Item.Items;

/**
 * Gère le pannier d'un consommateur
 */
public class Cart implements Interactable{
    
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



    public void changeQuantity(UUID uuid, Integer quantity){
        if(quantity<1){
            shoppingList.remove(uuid); // Si la quantité d'un produit devientt nul dans le panier, alors l'entrée de ce produit est supprimé de la ArrayList
        }else{
            shoppingList.put(uuid, quantity);
        }
    }

    /**
     * Utilisé pour l'intéractin avec le consommateur
     * @throws IOException
     */
    public void displayforchange() throws IOException{
        System.out.println("Articles dans le panier : ");
        ArrayList<UUID> foundItems = new ArrayList<>();
        int i = 0;
        for (HashMap.Entry<UUID,Integer> element : shoppingList.entrySet()) {
            i++;
            System.out.println("["+i+"] "+ Items.getValue(element.getKey()) +", qauntité("+element.getValue()+")") ;
            foundItems.add(element.getKey());
        }
        if(foundItems.isEmpty()){
            System.out.println("Aucun Article dans le panier");
            return;
        }
        String input = userInput("\ndelete : Retirer un élément du panier\n"+
            "change : changer la quantité d'un article dans le panier\n"+
            "Entrez une autre valeur quelconque pour quitter\n");
            try{
                switch (input) {
                    case "delete":
                        shoppingList.remove(foundItems.get(userIntegerInput("Entrez le [numéro] du produit : ")-1));
                        System.out.println("Panier modifié");
                        break;
                    case "change":
                        changeQuantity(foundItems.get(userIntegerInput("Entrez le [numéro] du produit : ")-1), userIntegerInput("Entrez la nouvelle quantité : "));
                        System.out.println("Panier modifié");
                    default:
                        break;
                }
            } catch(IndexOutOfBoundsException e){
                System.out.println("Erreur : Le nombre entrée ne fait pas partie des valeurs proposées");
            }
        }
    
    public void display(){
        System.out.println("Articles dans le panier : ");
        int i = 0;
        for (HashMap.Entry<UUID,Integer> element : shoppingList.entrySet()) {
            i++;
            System.out.println("["+i+"] "+ Items.getValue(element.getKey()) +", qauntité("+element.getValue()+")");
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

    /**
     * @return Prix de l'ensemble du panier du consommateur avant l'achat
     */
    public Double totalPrice(){
        Double price = 0.;
        for (HashMap.Entry<UUID,Integer> item : shoppingList.entrySet()) {
            price += Marketplace.getPrice(item.getKey())*item.getValue();
        }
        return price;
    }

}