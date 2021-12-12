package Users;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import Gestionnary.Marketplace;
import Interface.Searchable;
import Item.Items;

public class Customer extends User implements Searchable{

    private Cart cart = new Cart();

    public Customer(String username, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        super(username, password);
    }

    public void addToCart(UUID uuid) throws NumberFormatException, IOException{
        int quantity = inputQuantity();
        if(quantity <= Marketplace.getQuantity(uuid)){
            this.cart.addProduct(uuid, quantity);
        } else {
            System.out.println("Erreur : Les stocks pour cet article sont inférieur à "+ quantity);
        }
    }

    public void displayCart(){
        this.cart.display();
    }

    public void payment(){
        this.cart.buy();
        System.out.println("Achat effectué");
    }

    public void search() throws NumberFormatException, IOException{
        UUID uuid = searchMarketplace();
        if (uuid != null) {
            askCustomerUpponItem(uuid);
        }
    }

    public void askCustomerUpponItem(UUID uuid) throws NumberFormatException, IOException{
        System.out.println("***"+ Items.getSimpleName(uuid) +"***\n"+
        "buy : Ajouter au panier\n"+
        "back : quitter"
        );
        String in = userInput();
        switch(in){
            case "buy":
                addToCart(uuid);
                break;
            case "back":
                break;
        }
    
    }

    public void buyCart() throws IOException{
        displayCart();
        System.out.println("Prix total : "+ cart.totalPrice()+"€\n");
        System.out.println("Confirmer ? (y/n)");
        String in = userInput();
        switch(in){
            case "y":
                payment();
                break;
            case "n":
                break;
        }
    }

    public void interact(String password) throws NumberFormatException, IOException, NoSuchAlgorithmException{
        if(encryptedPassword.verify(password)){
            Boolean running = true;
            System.out.println(" *********** BIENVENUE : "+this.username+" (compte client) *********** \n\n"+
                "Entrez 'help' pour afficher les commandes.");
            nextCommand();
            while(running){
                String in = userInput();
                switch (in) {
                    case "search":
                        search();
                        nextCommand();
                        break;
                    case "buy":
                        buyCart();
                        nextCommand();
                        break;
                    case "display":
                        Marketplace.displayClient();
                        nextCommand();
                        break;
                    case "changePassword":
                        changePassword(userInput("Entrez nouveau mot de passe : "));
                        nextCommand();
                        break;
                    case "help":
                        displayCommandHelp();
                        nextCommand();
                        break;
                    case "exit":
                        running = false;
                        break;
                        default:
                        System.out.println("Commande non trouvée");
                        break;
                    }
                }
            System.out.println("*********** DECONNEXION DE "+this.username+"  *********** ");
        } else{
            System.out.println("Mot de passe incorrect");
        }
    }

    void displayCommandHelp(){
        System.out.println("Liste des commandes disponibles: \n\n"
        +" * search : Rechercher un article\n"
        +" * buy : Commander les articles dans le panier\n"
        +" * display : Afficher les articles en vente\n"
        +" * changePassword : Changer votre mot de passe \n"
        +" * help : Afficher la liste des commandes disponibles\n"
        +" * exit : Quitter le programme \n"
        );
    }



}