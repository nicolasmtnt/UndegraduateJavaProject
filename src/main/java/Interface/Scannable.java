package Interface;

import Item.Item;

public interface Scannable{
    static boolean staticScan(Item item, String str){
        if(item.toString().toLowerCase().contains(str.toLowerCase())){
            return true;
        }
        return false;
    }

    default boolean scan(Item item, String str){
        if(item.toString().toLowerCase().contains(str.toLowerCase())){
            return true;
        }
        return false;
    }
}
