package servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.PointSimple;
import model.ModelPoint;
import service.PointService;
import service.PointServiceImpl;
import service.ReportService;
import service.ReportServiceImpl;
import util.EmailSender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {
    private PointService pointService = PointServiceImpl.getInstance();
    private ReportService reportService = ReportServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String[] str = req.getPathInfo().split("/");
        long id = Long.parseLong(req.getParameter("id"));
        //PointInfo point = dtoService.getPoint(id);
        List<PointSimple> points = reportService.getPointFromReport(id);
        Gson gson = new Gson();
        String json = gson.toJson(points);
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        Type typeListPoint = new TypeToken<List<PointSimple>>(){}.getType();
        List<Long> garbageIds = Arrays.asList(gson.fromJson(req.getParameter("garbageIds"),Long[].class));
        List<ModelPoint> garbagePoints = pointService.getPointsById(garbageIds);
        List<PointSimple> red = pointService.convertPointType(garbagePoints);
        List<PointSimple> green = gson.fromJson(req.getParameter("urns"), typeListPoint);
        EmailSender emailSender = new EmailSender(red, green);
//        emailSender.send();
        reportService.createReport(green);
    }
}
