package servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.PointSimple;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        Type typeListPoint = new TypeToken<List<PointSimple>>(){}.getType();
        List<PointSimple> red = gson.fromJson(req.getParameter("red"), typeListPoint);
        List<PointSimple> green = gson.fromJson(req.getParameter("green"), typeListPoint);

    }
}
