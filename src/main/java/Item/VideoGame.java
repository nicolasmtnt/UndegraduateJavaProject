package Item;

public final class VideoGame extends Item{
    String publisher;
    String developer;
    int metascore;

    public VideoGame(String title, String year, String publisher, String developer) {
        super(title, year);
        this.publisher = publisher;
        this.developer = developer;
    }

    @Override
    public String toString() {
        return "VideoGame, " + title + ", " + year + ", " + developer + ", " + publisher;
    }
    

}
