import Item.Item;

public interface Scannable {
    public static boolean scan(Item item, String str){
        if(item.toString().toLowerCase().contains(str.toLowerCase())){
            return true;
        }
        return false;
    }
}
