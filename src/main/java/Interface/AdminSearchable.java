package Interface;

import java.util.ArrayList;
import java.util.UUID;

import Gestionnary.Stock;
import Item.Items;

public interface AdminSearchable extends Searchable{
    default UUID searchStock(){
        try{
            ArrayList<UUID> foundItems = Stock.search(userInput("Recherchez un produit (nom, auteurs ...) : "));
            if(foundItems.size()==0){
                throw new UnsatisfableSearchResultException();
            }
            return foundItems.get(Integer.parseInt(userInput("Entrez le [numéro] du produit : "))-1);
        } catch(NumberFormatException e){
            System.out.println("Erreur : La valeur entrée n'est pas valable");
            return searchStock();
        } catch(IndexOutOfBoundsException e){
            System.out.println("Erreur : Le nombre entrée ne fait pas partie des valeurs proposées");
            return searchStock();
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    default UUID searchItem(){
        try{
            ArrayList<UUID> foundItems = Items.search(userInput("Recherchez un produit (nom, auteurs ...) : "));
            if(foundItems.size()==0){
                throw new UnsatisfableSearchResultException();
            }
            return foundItems.get(userIntegerInput("Entrez le [numéro] du produit : ")-1);
        } catch(IndexOutOfBoundsException e){
            System.out.println("Erreur : Le nombre entré ne fait pas partie des valeurs proposées");
            return searchItem();
        } catch(Exception e){
            System.out.println(e.getMessage()+"sheh");
            return null;
        }
    }
}
