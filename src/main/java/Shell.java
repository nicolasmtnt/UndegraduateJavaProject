import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;
import Gestionnary.Marketplace;
import Gestionnary.Stock;
import Item.Items;

public class Shell {
    public static void main(String[] args) throws IOException {
        Items.add("album", new String[]{"Dark Side of the Moon", "1988", "Pink Floyd", "Universal"});
        Items.add("videogame", new String[]{"Super Mario bros","2019","Nintendo","Nintendo"});
        Items.add("movie", new String[]{"Star Wars","2010","George Lucas","Lucasfilm"});
        Items.add("movie", new String[]{"Star Wars","2010","George Lucas","Lucasfilm"});
        //
        Boolean running = true;
        System.out.println(" *********** LANCEMENT DU PROGRAMME *********** \n\n"+
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
                    Marketplace.display();
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
                case "help":
                    displayCommandHelp();
                    break;
                case "exit":
                    running = false;
                    System.out.println("*********** ARRET DU PROGRAMME *********** ");
                    break;
                default:
                    System.out.println("Commande non trouvée");
                    break;
            }
        }
    }
    
    static void displayCommandHelp(){
        System.out.println("Liste des commandes disponibles: \n\n"
        +" * new : Ajouter un article dans le système \n"
        +" * display : Afficher les articles enregistrés dans le système \n"
        +" * displaystock : Afficher les articles en stock\n"
        +" * displaysell : Afficher les articles en vente\n"
        +" * addstock : Ajouter un produit au stock\n"
        +" * removestock : Retirer un produit du stock\n"
        +" * addsell : Ajouter un produit (au préalable en stock) à la vente \n"
        +" * removesell : Retirer un produit de la vente \n"
        +" * help : Afficher la liste des commandes disponibles\n"
        +" * exit : Quitter le programme \n"
        );
    }

    static void addItem() throws IOException{
        String category = userCategoryInput();
        if(category == ""){
            return;
        }
        System.out.println("Quelle est le titre de l'article ?");
        String title = userInput();
        System.out.println("Quelle est la date de sortie de l'article ?");
        String annee = userInput();
        switch (category) {
            case "jeuxvideo":
                Items.add("videogame", new String[]{title,annee,userPublisherInput(),userDeveloperInput()});
                break;

            case "film":
                Items.add("movie", new String[]{title,annee,userDirectorInput(),userDistributionCompanyInput()});
                break;
            
            case "album":
                Items.add("album", new String[]{title, annee, userArtistInput(), userLabelInput()});
                break;
            default:
                break;
        }
        
        //String[] commonAttributs = askForCommonAttributs();

    }


    static String userCategoryInput() throws IOException{
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
            return "";
    }


    static String userInput() throws IOException{
        return new BufferedReader(new InputStreamReader(System.in)).readLine();

    }

    static String userInput(String message) throws IOException{ // On utilise la surcharge
        System.out.println(message);
        return new BufferedReader(new InputStreamReader(System.in)).readLine();

    }

    static String userPublisherInput() throws IOException{
        System.out.println("Entrez l'éditeur du jeux vidéo : ");
        return userInput();
    }

    static String userDeveloperInput() throws IOException{
        System.out.println("Entrez le developpeur du jeux vidéo : ");
        return userInput();
    }

    static String userDirectorInput() throws IOException{
        System.out.println("Entrez le nom du réalisateur : ");
        return userInput();
    }

    static String userDistributionCompanyInput() throws IOException{
        System.out.println("Entrez le nom du distributeur : ");
        return userInput();
    }

    static String userArtistInput() throws IOException{
        System.out.println("Entrez le nom de l'artiste : ");
        return userInput();
    }

    static String userLabelInput() throws IOException{
        System.out.println("Entrez le nom du label : ");
        return userInput();
    }



    static void addStock() throws IOException{
        try {
            ArrayList<UUID> foundItems = Items.search(userInput("Recherchez un produit (nom, auteurs ...) : "));
            if(foundItems.size()==0){
                throw new UnsatisfableSearchResultException();
            }
            UUID uuid = foundItems.get(Integer.parseInt(userInput("Entrez le [numéro] du produit : "))-1);
            int quantity = Integer.parseInt(userInput("Entrez la quantité : "));
            Stock.add(uuid, quantity);
        } catch (NumberFormatException e) {
            System.out.println("Erreur : La valeur entrée n'est pas valable");
        } catch(IndexOutOfBoundsException e){
            System.out.println("Erreur : Le nombre entrée ne fait pas partie des valeurs proposées");
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


        static void addSell() throws IOException{
            try {
                ArrayList<UUID> foundItems = Stock.search(userInput("Recherchez un produit (nom, auteurs ...) : "));
                if(foundItems.size()==0){
                    throw new UnsatisfableSearchResultException();
                }
                UUID uuid = foundItems.get(Integer.parseInt(userInput("Entrez le [numéro] du produit : "))-1);
                int quantity = Integer.parseInt(userInput("Entrez la quantité (entier): "));
                float price = Float.parseFloat(userInput("Entrez le prix (ex : 19.99): "));
                Marketplace.add(uuid, quantity, price);
            } catch (NumberFormatException e) {
                System.out.println("Erreur : La valeur entrée n'est pas valable");
            } catch(IndexOutOfBoundsException e){
                System.out.println("Erreur : Le nombre entrée ne fait pas partie des valeurs proposées");
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
    }


    static void removesell() throws IOException{
        try{
            ArrayList<UUID> foundItems = Marketplace.search(userInput("Recherchez un produit (nom, auteurs ...) : "));
            if(foundItems.size()==0){
                throw new UnsatisfableSearchResultException();
            }
            UUID uuid = foundItems.get(Integer.parseInt(userInput("Entrez le [numéro] du produit : "))-1);
            int quantity = Integer.parseInt(userInput("Quelle quantité voulez vous enlever de la vente : "));
            Marketplace.substract(uuid, quantity);
        } catch(NumberFormatException e){
            System.out.println("Erreur : La valeur entrée n'est pas valable");
        } catch(IndexOutOfBoundsException e){
            System.out.println("Erreur : Le nombre entrée ne fait pas partie des valeurs proposées");
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    static void removeStock(){
        try{
            ArrayList<UUID> foundItems = Stock.search(userInput("Recherchez un produit (nom, auteurs ...) : "));
            if(foundItems.size()==0){
                throw new UnsatisfableSearchResultException();
            }
            UUID uuid = foundItems.get(Integer.parseInt(userInput("Entrez le [numéro] du produit : "))-1);
            int quantity = Integer.parseInt(userInput("Entrez la quantité (entier): "));
            Stock.substract(uuid, quantity);
        } catch (NumberFormatException e) {
                System.out.println("Erreur : La valeur entrée n'est pas valable");
        } catch(IndexOutOfBoundsException e){
            System.out.println("Erreur : Le nombre entrée ne fait pas partie des valeurs proposées");
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    static void nextCommand(){
        System.out.println("\nNouvelle commande :");
    }
}

class UnsatisfableSearchResultException extends Exception{
    public UnsatisfableSearchResultException(){
        super("Aucun élement trouvé");
    }
}