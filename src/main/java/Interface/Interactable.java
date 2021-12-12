package Interface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public interface Interactable {

    default String userInput() throws IOException{
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    default String userInput(String message) throws IOException{ // On utilise la surcharge
        System.out.println(message);
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    default Integer userIntegerInput(String message) throws IOException {
        String strNum = userInput(message);
        try {
            return Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            System.out.println("Erreur : La valeur entré n'est pas un entier");
            return userIntegerInput(message);
        }
    }

    default Double userDoubleInput(String message) throws IOException {
        String strNum = userInput(message);
        try {
            return Double.parseDouble(strNum);
        } catch (NumberFormatException e) {
            System.out.println("Erreur : La valeur entré n'est pas un flottant");
            return userDoubleInput(message);
        }
    }

    default Integer inputQuantity() throws NumberFormatException, IOException{
        int quantity = userIntegerInput("Entrez la quantité : ");
        if (0 < quantity) {
            return quantity;
        } else {
            System.out.println("Erreur : la quantité doit etre strictement postive");
            return inputQuantity();
        }
    }

    default Double inputPrice() throws NumberFormatException, IOException{
        double price = userDoubleInput("Entrez le prix: ");
        if (0<price) {
            return Math.round(price*100.0)/100.0;
        } else {
            System.out.println("Erreur : le prix n'est pas strictement postif");
            return inputPrice();
        }
    }

    default String inputYear(String message) throws IOException{
        return userIntegerInput(message).toString();
    }
}
