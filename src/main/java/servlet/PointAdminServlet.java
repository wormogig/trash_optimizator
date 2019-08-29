package servlet;

import com.google.gson.Gson;
import dto.PointInfoAdmin;
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

@WebServlet("/point/admin")
public class PointAdminServlet extends HttpServlet {
    DtoService dtoService = DtoServiceImpl.getInstance();
    PointService pointService = PointServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        PointInfoAdmin point = dtoService.getPointAdmin(id);
        Gson gson = new Gson();
        String json = gson.toJson(point);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("delete")) {
            try {
                long id = Long.parseLong(req.getParameter("id"));
                pointService.deletePointById(id);
            } catch (Exception e) {
                System.out.println("Удаление точки не удалось");
            }
        }
    }
}
