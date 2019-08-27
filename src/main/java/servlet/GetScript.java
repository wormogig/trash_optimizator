package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getscript")
public class GetScript extends HttpServlet {
    //        По идее нужно проверить, что никакие параметры не переданы?
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/data.html");
        dispatcher.forward(req, resp);
    }
}
