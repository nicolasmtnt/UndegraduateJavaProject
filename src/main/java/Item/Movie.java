package Item;

public final class Movie extends Item{
    String director;
    String studio;

    public Movie(String title, String year, String director, String studio) {
        super(title, year);
        this.director = director;
        this.studio = studio;
    }

    @Override
    public String toString() {
        return "Movie," + title +","+ year + "," + director + "," + studio;
    }

    

}
