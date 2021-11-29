package Item;

public class Album extends Item {
    String label;
    String artist;

    public Album(String title, String year, String artist, String label) {
        super(title, year);
        this.artist = artist;
        this.label = label;
    }

    @Override
    public String toString() {
        return "Album," + title + "," + year + "," + artist + "," + label;
    }

}
