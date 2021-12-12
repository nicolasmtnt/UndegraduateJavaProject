package Users;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import Gestionnary.Marketplace;
import Gestionnary.Stock;
import Interface.AdminSearchable;
import Item.Items;

public class Employee extends User implements AdminSearchable{
    public Employee(String username, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        super(username, password);
    }
    // private void removeCustomer(String username) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    //     Customers.remove(username);
    // }

    protected void addStock() throws NumberFormatException, IOException{
        UUID uuid = searchItem();
        if(uuid != null){
            Integer quantity = inputQuantity();
            Stock.add(uuid, quantity);
        }
    }

    protected void removeStock() throws NumberFormatException, IOException{
        UUID uuid = searchStock();
        if(uuid != null){
            Integer quantity = inputQuantity(); 
            Stock.remove(uuid, quantity);
        }
    }

    protected void addSell() throws NumberFormatException, IOException{
        UUID uuid = searchStock();
        if(uuid != null){
            Integer quantity = inputQuantity();
            Double price = inputPrice();
            Marketplace.add(uuid, quantity , price);
        }
    }

    protected void removesell() throws NumberFormatException, IOException{
        UUID uuid = searchMarketplace();
        if(uuid != null){
            Integer quantity = inputQuantity();
            Marketplace.remove(uuid, quantity);
        }
    }

    public void interact(String password) throws NumberFormatException, IOException, NoSuchAlgorithmException{
        if(encryptedPassword.verify(password)){
            Boolean running = true;
            System.out.println(" *********** BIENVENUE : "+this.username+" (compte employé) *********** \n\n"+
                "Entrez 'help' pour afficher les commandes.");
            nextCommand();
            while(running){
                String in = userInput();
                switch (in) {
                    case "new": 
                        addItem();
                        nextCommand();
                        break;
                    case "display":
                        Items.display();
                        nextCommand();
                        break;
                    case "addstock":
                        addStock();
                        nextCommand();
                        break;
                    case "displaystock":
                        Stock.display();;
                        nextCommand();
                        break;
                    case "removestock":
                        removeStock();
                        nextCommand();
                        break;
                    case "displaysell":
                        Marketplace.displayManager();
                        nextCommand();
                        break;
                    case "addsell":
                        addSell();
                        nextCommand();           
                        break;
                    case "removesell":
                        removesell();
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

    void addItem() throws IOException{
        String category = userCategoryInput();
        String title = userInput("Quelle est le titre de l'article ?");
        String annee = inputYear("Quelle est la date de sortie de l'article ?");
        switch (category) {
            case "jeuxvideo":
                Items.add("videogame", new String[]{title,annee,userInput("Entrez l'éditeur du jeux vidéo : "),userInput("Entrez le developpeur du jeux vidéo : ")});
                break;
            case "film":
                Items.add("movie", new String[]{title,annee,userInput("Entrez le nom du réalisateur : "),userInput("Entrez le nom du distributeur : ")});
                break;
            case "album":
                Items.add("album", new String[]{title, annee, userInput("Entrez le nom de l'artiste : "), userInput("Entrez le nom du label : ")});
                break;
            default:
                break;
        }
    }

    String userCategoryInput() throws IOException{
        System.out.println("Quelle est la catégorie de l'article ?\n"
            +" * jeuxvideo : Jeux Vidéo \n"
            +" * film : Film \n"
            +" * album : Album");
        String category = userInput();
        for (String str : new String[]{"jeuxvideo","album","film"}) {
            if (category.equals(str)){
                return category;
            }
        }
        System.out.println("Opération impossible : cette catégorie n'est pas prise en charge.\n");
        return userCategoryInput();
    }

    void displayCommandHelp(){
        System.out.println("Liste des commandes disponibles: \n\n"
        +" * new : Ajouter un article dans le système \n"
        +" * display : Afficher les articles enregistrés dans le système \n"
        +" * displaystock : Afficher les articles en stock\n"
        +" * displaysell : Afficher les articles en vente\n"
        +" * addstock : Ajouter un produit au stock\n"
        +" * removestock : Retirer un produit du stock\n"
        +" * addsell : Ajouter un produit (au préalable en stock) à la vente \n"
        +" * removesell : Retirer un produit de la vente \n"
        +" * changePassword : Changer votre mot de passe \n"
        +" * help : Afficher la liste des commandes disponibles\n"
        +" * exit : Quitter le programme \n"
        );
    }

}
