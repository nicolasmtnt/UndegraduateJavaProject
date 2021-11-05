package Item;

public class Item{
    String title;
    String year;
    
    public Item(String title, String year) {
        this.title = title;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Item [title=" + title + ", year=" + year + "]";
    }
    
    /* ---------- Getter / Setter ---------- */


    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    

    
    
    
}
