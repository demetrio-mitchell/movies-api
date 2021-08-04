import com.google.gson.Gson;
import data.Movie;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "MovieServlet", urlPatterns = "/movies")
public class MovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        try {
            PrintWriter out = response.getWriter();
            Movie movie = new Movie(2, "Tenet", "2021", "5", "Armed with only one word, Tenet," +
                    " and fighting for the survival of the entire world, a Protagonist journeys\n" +
                    "through a twilight world of international espionage on a mission that will unfold in something beyond real time.");
            String movieString = new Gson().toJson(movie);

            out.println(movieString);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
