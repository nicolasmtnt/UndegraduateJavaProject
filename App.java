import mypackage.*;


public class App{
    public static void main(String[] args){

        Items.add("Porsche","911");
        Items.add("Renault","Zoé");
        Items.display();
        System.out.println(Items.getUUID("Porsche","911"));



    }

}