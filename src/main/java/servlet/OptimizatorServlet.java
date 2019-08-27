package servlet;

import com.google.gson.Gson;
import dto.CalcRequest;
import dto.UrnPoint;
import model.ModelPoint;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/optim")
public class OptimizatorServlet extends HttpServlet {
    PointService pointService = PointServiceImpl.getInstance();
    DtoService dtoService = DtoServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
//        String json = req.getReader().readLine();
//        CalcRequest calcRequest = gson.fromJson(json, CalcRequest.class);
//        int numUrn = calcRequest.getNumUrn();
//        List<ModelPoint> points = pointService.getPointsById(calcRequest.getPointIds());
        List<ModelPoint> points = pointService.getPoints();
        int numUrn = 2;
        double[][] readyPoints = pointService.preparePointsArray(points);
        double[][] resultPoints = new Kmeans(readyPoints, numUrn).getCentersArray();
        List<UrnPoint> urnPoints = dtoService.createListUrnPoint(resultPoints);
        String respJson = gson.toJson(urnPoints);
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println(respJson);
    }
}
