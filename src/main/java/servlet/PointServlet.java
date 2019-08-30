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
        PointInfo point = dtoService.getPoint(id);
        Gson gson = new Gson();
        String json = gson.toJson(point);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().readLine();
        Gson gson = new Gson();
        Message message = new Message("");
        try {
            PointSend point = gson.fromJson(json, PointSend.class);
            dtoService.addPoint(point);
            message.setMessage("Добавление точки прошло успешно");
        } catch (Exception e) {
            System.out.println("Входной Json некорректен");
            message.setMessage("Добавление точки не удалось");
        }
        String mess = gson.toJson(message);
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println(mess);
    }

}
