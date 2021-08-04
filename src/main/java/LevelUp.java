import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "LevelUpServlet", urlPatterns = "/level-up")
public class LevelUp extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");
        try {
            PrintWriter out = response.getWriter();
            out.println("Level Up! Nice!!");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
