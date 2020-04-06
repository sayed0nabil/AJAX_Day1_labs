import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/checkusername")
public class CheckUserNameServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        String userName = req.getParameter("username");
        if(userName != null){
            if(userName.equals("sayed0nabil")){
                res.setStatus(200);;
            }else{
                res.setStatus(404);
            }
        }else{
            res.setStatus(404);
        }
    }
}
