package Interface;
import Item.Item;

public interface Scannable {
    public static boolean staticScan(Item item, String str){
        if(item.toString().toLowerCase().contains(str.toLowerCase())){
            return true;
        }
        return false;
    }

    public default boolean scan(Item item, String str){
        if(item.toString().toLowerCase().contains(str.toLowerCase())){
            return true;
        }
        return false;
    }
}
