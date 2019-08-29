package servlet;

import com.google.gson.Gson;
import dto.UrnPoint;
import model.ModelPoint;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/optim")
public class OptimizatorServlet extends HttpServlet {
    PointService pointService = PointServiceImpl.getInstance();
    DtoService dtoService = DtoServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        List<Long> garbageIds = Arrays.asList(gson.fromJson(req.getParameter("garbageIds"),Long[].class));
        int urnCount = Integer.parseInt(req.getParameter("urnCount"));
        List<ModelPoint> points = pointService.getPointsById(garbageIds);
        double[][] readyPoints = pointService.preparePointsArray(points);
        double[][] resultPoints = new Kmeans(readyPoints, urnCount).getCentersArray();
        List<UrnPoint> urnPoints = dtoService.createListUrnPoint(resultPoints);
        String respJson = gson.toJson(urnPoints);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(respJson);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
