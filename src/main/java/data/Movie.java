package data;

public class Movie {
    private int id;
    private String title;
    private String year;
    private String director;
    private String actors;
    private String imdbID;
    private String poster;
    private String genre;
    private String plot;
    private String rating;

    public Movie(){
    }

    public Movie(String title, String year, String rating, String plot, int id) {

        this.title = title;
        this.year = year;
        this.rating = rating;
        this.plot = plot;
        this.id = id;
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

    public String getRating() {
        return rating;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
