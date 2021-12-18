package Item;

public final class Movie extends Item{
    String director;
    String studio;

    public Movie(String title, String year, String director) {
        super(title, year);
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie," + title +","+ year + "," + director;
    }

    

}
