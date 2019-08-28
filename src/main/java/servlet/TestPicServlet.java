package servlet;

import service.PointService;
import service.PointServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test_pic")
public class TestPicServlet extends HttpServlet {
    PointService pointService = PointServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String pic1 = "<img src='data:image/jpeg;base64," + pointService.getPoint(id).getImage() + "'/>";
//        String pic2 = "<img src='data:image/jpeg;base64," + pointService.getPoint(2).getImage() + "'/>";
        String pic = "<html><head></head><body> " + pic1 + " </body> </html>";
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println(pic);
    }
}
