import beans.Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@WebServlet("/messenger")
public class ChatServlet  extends HttpServlet {


    private String createJson(List<Message> list){
        Gson gson = new Gson();
        return gson.toJson( list, new TypeToken<List<Message>>() {}.getType());
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        List<Message> list = new ArrayList<Message>();
        list.add(new Message("sayed.nabil", "Hi From Other Side"));
        config.getServletContext().setAttribute("msgs", list);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
         String json = createJson((ArrayList)req.getServletContext().getAttribute("msgs"));
         res.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Message> list = (ArrayList)req.getServletContext().getAttribute("msgs");
        String username = req.getParameter("username");
        String msg = req.getParameter("message");
        list.add(new Message(username, msg));
        req.getServletContext().setAttribute("msgs", list);
        String json = createJson(list);
        res.getWriter().write(json);
    }

}
