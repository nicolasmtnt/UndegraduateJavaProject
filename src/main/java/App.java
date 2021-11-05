import Gestionnary.*;
import Item.Items;

public class App{
    public static void main(String[] args){

        System.out.println("\n ************ DEBUT DU PROGRAMME ************");

        // Création d'élements : 
        Items.add("album", new String[]{"Dark Side of the Moon", "1988", "Pink Floyd", "Universal"});
        Items.add("videogame", new String[]{"Super Mario bros","2019","Nintendo","Nintendo","81"});
        Items.add("movie", new String[]{"Star Wars","2010","George Lucas","Lucasfilm"});
        Items.add("movie", new String[]{"Star Wars","2010","George Lucas","Lucasfilm"}); // ajouter truc pour empecher doublon
        
        Stock.add("Movie", "Star Wars","2010", 10);
        Stock.add("Movie", "Star Wars","2010", 2);
        Stock.add("videogame","Super Mario bros","2019",23);
        Stock.display();
        System.out.println("\n");
        
        // Manipulation :
        Stock.toMarketplace("videogame","Super Mario bros","2019",10, 59.99);
        Marketplace.display();
        Stock.display();
        System.out.println("\n");

        Marketplace.toStock("videogame","Super Mario bros","2019",10);
        Marketplace.display();
        Stock.display();
        Marketplace.display();

        System.out.println(" ************ FIN DU PROGRAMME ************\n");

    }
}