package Item;

public class VideoGame extends Item{
    String publisher;
    String developer;
    int metascore;

    public VideoGame(String title, String year, String publisher, String developer, String metascore) {
        super(title, year);
        this.publisher = publisher;
        this.developer = developer;
        this.metascore = Integer.parseInt(metascore);
    }

    @Override
    public String toString() {
        return "VideoGame [title=" + title + ", year=" + year + ", developer=" + developer + ", metascore=" + metascore + ", publisher=" + publisher + "]";
    }
    

}
