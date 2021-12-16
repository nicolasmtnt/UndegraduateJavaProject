package Users;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import Gestionnary.Marketplace;
import Gestionnary.Stock;
import Item.Items;

public final class Manager extends Employee {

    public Manager(String username, String password, Boolean encrypt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        super(username, password,encrypt);
    }

    private void removeEmployee(String username) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        Staff.remove(username);
    }

    private void addEmployee(String username, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        Staff.add(username,password);
    }

    private void displayStaff(){
        Staff.display();
    }

    @Override
    public void interact(String password) throws NumberFormatException, IOException, NoSuchAlgorithmException {
        if(encryptedPassword.verify(password)){
            Boolean running = true;
            System.out.println(" *********** MENU EMPLOYEE : "+this.username+" (compte Administrateur) *********** \n\n"+
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
                        this.changePassword(userInput("Entrez nouveau mot de passe : "));
                        nextCommand();
                        break;
                    case "removeEmployee":
                        this.removeEmployee(userInput("Entrez le nom d'utilisateur de l'employée : "));
                        nextCommand();
                        break;
                    case "addEmployee":
                        this.addEmployee(userInput("Entrez le nom d'utilisateur :"),userInput("Entrez le mot de passe :"));
                        nextCommand();
                        break;
                    case "displayStaff":
                        this.displayStaff();
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
            System.out.println("*********** DECONNEXION DE "+this.username+" (compte Administrateur) *********** ");
            } else{
                System.out.println("Mot de passe incorrect");
            }
        }
    

    @Override
    void displayCommandHelp() {
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
        +" * addEmployee : Ajouter un employé \n"
        +" * removeEmployee : Retirer un employé \n"
        +" * help : Afficher la liste des commandes disponibles\n"
        +" * exit : Deconnexion \n"
        );
    }

    

    
}
