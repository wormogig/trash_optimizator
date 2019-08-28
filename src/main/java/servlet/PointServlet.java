package servlet;

import com.google.gson.Gson;
import dto.Message;
import dto.PointInfo;
import dto.PointSend;
import service.DtoService;
import service.DtoServiceImpl;
import service.PointService;
import service.PointServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/point/*")
public class PointServlet extends HttpServlet {
    DtoService dtoService = DtoServiceImpl.getInstance();
    PointService pointService = PointServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] str = req.getPathInfo().split("/");
        long id = Long.parseLong(str[str.length-1]);
        boolean isAdmin = Boolean.parseBoolean(req.getParameter("admin")) ;
        PointInfo point = null;
        if (isAdmin) {
            point = dtoService.getPointAdmin(id);
        } else {
            point = dtoService.getPoint(id);
        }
        Gson gson = new Gson();
        String json = gson.toJson(point);
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().readLine();
        Gson gson = new Gson();
        PointSend point = gson.fromJson(json, PointSend.class);
        dtoService.addPoint(point);
        //
        Message message = new Message();
        String mess = gson.toJson(message);
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println(mess);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        pointService.deletePointById(id);
    }
}
