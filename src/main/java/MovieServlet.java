import com.google.gson.Gson;
import data.DaoFactory;
import data.Movie;
import data.MoviesDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "MovieServlet", urlPatterns = "/movies")
public class MovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        try {
            PrintWriter out = response.getWriter();

            // eventually get movies from the database
//            Movie movie = new Movie(2, "Moonrise Kingdom","2012","Wes Anderson","Bill Murray","100","No Poster","Comedy","Kids go on an adventure");

            // We are letting the "DaoFactory" do the work
            MoviesDao moviesDao = DaoFactory.getMoviesDao(DaoFactory.ImplType.IN_MEMORY);

            // turn into a Json string
            String moviesString = new Gson().toJson(moviesDao.all());

            // inject into response
            out.println(moviesString);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            // get the stream of characters from the request (eventually becomes our movie)
            BufferedReader reader = request.getReader();

            // turns that stream into an array of Movies
            Movie[] movies = new Gson().fromJson(reader, Movie[].class);
            DaoFactory.getMoviesDao(DaoFactory.ImplType.IN_MEMORY).insert(movies[0]);

            // sout out properties of each movie
            for (Movie movie : movies) {
                System.out.println(movie.getId());
                System.out.println(movie.getTitle());
                System.out.println(movie.getDirector());
            }

        } catch (IOException ex) {
            out.println(new Gson().toJson(ex.getLocalizedMessage()));
            response.setStatus(500);
            ex.printStackTrace();
            return;

        }
        // write a response body and set the status code to 200.
        out.println(new Gson().toJson("{message: \" Movies POST was successful\"}"));
        response.setStatus(200);

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        PrintWriter out = null;

        try {
            out = response.getWriter();

            Movie movie = new Gson().fromJson(request.getReader(), Movie.class);
            DaoFactory.getMoviesDao(DaoFactory.ImplType.IN_MEMORY).update(movie);
        } catch (SQLException e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(500);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(400);
            e.printStackTrace();
            return;
        }


        out.println(new Gson().toJson("{message: \" Movies PUT was successful\"}"));
        response.setStatus(200);

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        PrintWriter out = null;

        try {
            out = response.getWriter();

            var id = new Gson().fromJson(request.getReader(), int.class);
            DaoFactory.getMoviesDao(DaoFactory.ImplType.IN_MEMORY).destroy(id);
        } catch (SQLException | IOException e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(500);
            e.printStackTrace();
            return;
        }

        out.println(new Gson().toJson("{message: \" Movies DELETE was successful\"}"));
        response.setStatus(200);

    }
}
