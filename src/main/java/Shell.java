import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Item.Items;

public class Shell {    public static void main(String[] args) throws IOException {
        Boolean running = true;
        System.out.println(" *********** LANCEMENT DU PROGRAMME *********** \n");
        displayCommandHelp();
        while(running){
            String in = userInput();
            switch (in) {
                case "new": 
                    addItem();
                    commandEnd();
                    break;
                case "display":
                    Items.display();
                    commandEnd();
                    break;
                case "help":
                    displayCommandHelp();
                    break;
                case "quitter":
                    running = false;
                    System.out.println("*********** ARRET DU PROGRAMME *********** ");
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
        +" * help : Afficher la liste des commandes disponibles\n"
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

    static void commandEnd(){
        System.out.println("\nNouvelle commande :");
    }
}
