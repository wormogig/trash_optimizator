package servlet;

import com.google.gson.Gson;
import dto.PointSimple;
import model.Report;
import service.PointService;
import service.PointServiceImpl;
import service.ReportService;
import service.ReportServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getreport")
public class GetReportServlet extends HttpServlet {

    private PointService pointService = PointServiceImpl.getInstance();
    private ReportService reportService = ReportServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
//        Тут воможен null
        Report report = reportService.getReportById(id);
        List<PointSimple> green = reportService.getUrnPointFromReport(report);
        List<PointSimple> red = reportService.getGarbagePointFromReport(report);
        Gson gson = new Gson();
        String jsonGreen = gson.toJson(green);
        String jsonRed = gson.toJson(red);
        String json = "[" + jsonGreen + "," + jsonRed + "]";
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
        resp.setStatus(HttpServletResponse.SC_OK);

    }
}
