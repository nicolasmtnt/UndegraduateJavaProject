package Interface;

import java.util.ArrayList;
import java.util.UUID;
import Gestionnary.Marketplace;


/**
 * Methode utilisé pour que l'utilisateur selectionne un article en vente:
 *  1. L'utilisateur entre une chaine de caractère (recerche)
 *  2. L'utilisateur entre un nombre entre 1 et n pour choisir un article parmi les n articles trouvés
 *  @return l'UUID de l'article seléctionné
 */

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

    default UUID searchMarketplaceForClient(){
        try{
            ArrayList<UUID> foundItems = Marketplace.searchForClient(userInput("Recherchez un produit (nom, auteurs ...) : "));
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

/**
 * Création de l'exception dans le cas ou aucun article a été trouvé durant la recherche
 */
class UnsatisfableSearchResultException extends Exception{
    public UnsatisfableSearchResultException(){
        super("Aucun élement trouvé");
    }
}