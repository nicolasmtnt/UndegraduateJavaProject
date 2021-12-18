package Item;

public final class VideoGame extends Item{
    String publisher;
    String platform;
    int metascore;

    public VideoGame(String title, String year, String publisher, String platform) {
        super(title, year);
        this.publisher = publisher;
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "VideoGame," + title + "," + year + "," + publisher + "," + platform;
    }
    

}
