package servlet;

import model.Category;
import model.ModelPoint;
import model.User;
import service.*;
import util.DataGenerator;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/test_data")
public class TestDataServlet extends HttpServlet {
    UserService userService = UserServiceImpl.getInstance();
    CategoryService categoryService = CategoryServiceImpl.getInstance();
    PointService pointService = PointServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataGenerator dataGenerator = new DataGenerator();

        List<User> users = dataGenerator.generateUsers(3);
        for (User user : users) {
            userService.addUser(user);
            System.out.println(user.getId());
        }

        List<Category> categories = dataGenerator.generateCategories();
        for (Category category : categories) {
            categoryService.addCategory(category);
            System.out.println(category.getId());
        }


        //Вызывать generatePoints можно только после вызова generateUsers и generateCategories, пока ~костыль
        List<ModelPoint> points = dataGenerator.generatePoints(20);
        for (ModelPoint point : points) {
            pointService.addPoint(point);
            System.out.println(point.getId());
        }

        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println("Тестовые данные внесены в БД");
        resp.sendRedirect("/");
    }

}
