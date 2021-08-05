import com.google.gson.Gson;
import data.Movie;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;

@WebServlet(name = "MovieServlet", urlPatterns = "/movies")
public class MovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        try {
            //get
            PrintWriter out = response.getWriter();
            // Eventually get movies form the database
            Movie movie = new Movie(2, "Tenet", "2021", "5", "Armed with only one word, Tenet," +
                    " and fighting for the survival of the entire world, a Protagonist journeys\n" +
                    "through a twilight world of international espionage on a mission that will unfold in something beyond real time.");
           //turn into a json string
            String movieString = new Gson().toJson(movie);

            //inject into response
            out.println(movieString);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response){

        response.setContentType("application/json");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            // get the stream of characters from the request (eventually becomes our movie)
            BufferedReader reader = request.getReader();

            // turns stream into array of movies
            Movie[] movies = new Gson().fromJson(reader, Movie[].class);

            // sout out each piece of the movie so we know the objects made it
            for (Movie movie : movies){
                System.out.println(movie.getId());
                System.out.println(movie.getTitle());
                System.out.println(movie.getYear());
                System.out.println(movie.getRating());
                System.out.println(movie.getPlot());
                System.out.println("==========================");
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        // write meaningful responses and set the response to 200
        out.println(new Gson().toJson("{message: \"Movies POST was successful\"}"));
        response.setStatus(200);
    }


    protected void doPut (HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");

        PrintWriter out = null;

        try {
            out = response.getWriter();

            BufferedReader reader = request.getReader();

            Movie[] movies = new Gson().fromJson(reader, Movie[].class);

            for (Movie movie : movies){
                System.out.println(movie.getId());
                System.out.println(movie.getTitle());
                System.out.println(movie.getYear());
                System.out.println(movie.getRating());
                System.out.println(movie.getPlot());
                System.out.println("==========================");
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        out.println(new Gson().toJson("{message: \"Movies PUT was successful\"}"));
        response.setStatus(200);
    }

    @Override
    protected void doDelete (HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        PrintWriter out = null;

        try {
            out = response.getWriter();

            BufferedReader reader = request.getReader();

            int id = new Gson().fromJson(reader, int.class);

            System.out.println("id: " + id + " has been deleted");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        out.println(new Gson().toJson("{message: \"Movies DELETE was successful\"}"));
        response.setStatus(200);
    }
}
