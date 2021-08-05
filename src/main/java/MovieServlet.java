import com.google.gson.Gson;
import data.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MovieServlet", urlPatterns ="/movies")
public class MovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");

        try {
            PrintWriter out = response.getWriter();
            // eventually get movies from the database
            Movie movie = new Movie(2, "Moonrise Kingdom","2012","Wes Anderson","Bill Murray","100","No Poster","Comedy","Kids go on an adventure");

            // turn into a Json string
            String movieString = new Gson().toJson(movie);
            // inject into response
            out.println(movieString);

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");

        PrintWriter out = null;
    try{

        out = response.getWriter();

        // get the stream of characters from the request (eventually becomes our movie)
        BufferedReader reader = request.getReader();

        // turns that stream into an array of Movies
    Movie[] movies = new Gson().fromJson(reader, Movie[].class);

    // sout out properties of each movie
    for (Movie movie : movies){
        System.out.println(movie.getId());
        System.out.println(movie.getTitle());
        System.out.println(movie.getDirector());
    }

    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }

    // write a response body and set the status code to 200.
    out.println(new Gson().toJson("{message: \" Movies POST was successful\"}"));
    response.setStatus(200);

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        PrintWriter out = null;

        try{
            out = response.getWriter();

            BufferedReader reader = request.getReader();

            Movie[] movies = new Gson().fromJson(reader, Movie[].class);

            for (Movie movie : movies){
                System.out.println(movie.getId());
                System.out.println(movie.getTitle());
                System.out.println(movie.getDirector());
            }

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        out.println(new Gson().toJson("{message: \" Movies PUT was successful\"}"));
        response.setStatus(200);

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");

        PrintWriter out = null;

        try{
        out = response.getWriter();

        BufferedReader reader = request.getReader();

        int id = new Gson().fromJson(reader, int.class);

            System.out.println("The movie id to delete is: " + id);

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        out.println(new Gson().toJson("{message: \" Movies DELETE was successful\"}"));
        response.setStatus(200);

    }
}
