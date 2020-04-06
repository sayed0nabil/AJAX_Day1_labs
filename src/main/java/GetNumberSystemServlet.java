import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getnumbersystems")
public class GetNumberSystemServlet extends HttpServlet {

    private String createJsonFile(int number){
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse("{\"octal\":\""+  Integer.toString(number, 8)  +"\"}");
        return gson.toJson(jsonElement);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String number = req.getParameter("number");
        if(number != null && !number.trim().isEmpty()){
            String json = createJsonFile(Integer.parseInt(number));
            res.getWriter().write(json);
        }
    }
}
