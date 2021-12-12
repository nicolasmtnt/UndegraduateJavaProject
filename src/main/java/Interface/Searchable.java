package Interface;

import java.util.ArrayList;
import java.util.UUID;
import Gestionnary.Marketplace;


public interface Searchable extends Interactable{
    default UUID searchMarketplace(){
        try{
            ArrayList<UUID> foundItems = Marketplace.search(userInput("Recherchez un produit (nom, auteurs ...) : "));
            if(foundItems.size()==0){
                throw new UnsatisfableSearchResultException();
            }
            return foundItems.get(Integer.parseInt(userInput("Entrez le [numéro] du produit : "))-1);
        } catch(NumberFormatException e){
            System.out.println("Erreur : La valeur entrée n'est pas valable");
            return searchMarketplace();
        } catch(IndexOutOfBoundsException e){
            System.out.println("Erreur : Le nombre entrée ne fait pas partie des valeurs proposées");
            return searchMarketplace();
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}

class UnsatisfableSearchResultException extends Exception{
    public UnsatisfableSearchResultException(){
        super("Aucun élement trouvé");
    }
}