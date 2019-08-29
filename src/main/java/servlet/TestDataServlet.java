package servlet;

import model.Category;
import model.ModelPoint;
import model.User;
import service.*;


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
        resp.setContentType("text/html; charset=UTF-8");
        List<Category> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            categories.add(new Category("Мелкий мусор", "Немного мусора вне урны"));
            categories.add(new Category("Куча мусора", "Большое количествоо скопившегося мусора"));
            categories.add(new Category("Свалка", "Огромная куча бытовых или строительных отходов"));
            for (Category category: categories) {
                categoryService.addCategory(category);
                System.out.println(category.getId());
            }
            List<User> users = new ArrayList<>();
            users.add(new User("User1"));
            users.add(new User("User2"));
            users.add(new User("User3"));
            for (User user: users) {
                userService.addUser(user);
                System.out.println(user.getId());
            }
            String img1 = "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";
            String img2 = "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";
            List<ModelPoint> points = new ArrayList<>();
            points.add(new ModelPoint(60.775243, 28.698896, categories.get(0), "12/04/1980 13:01:20", users.get(0),false, img1));
            points.add(new ModelPoint(60.774070, 28.698896, categories.get(0), "12/04/1980 13:01:20", users.get(1),false, img2));
            points.add(new ModelPoint(60.774453, 28.697518, categories.get(1), "12/04/1980 13:01:20", users.get(2), false, ""));
            points.add(new ModelPoint(60.774557, 28.696931, categories.get(1), "12/04/1980 13:01:20", users.get(0),false, ""));
            points.add(new ModelPoint(60.775584, 28.697220, categories.get(0), "12/04/1980 13:01:20", users.get(1),false, ""));
            points.add(new ModelPoint(60.775846, 28.696630, categories.get(1), "12/04/1980 13:01:20", users.get(2), false, ""));

            for (ModelPoint point: points) {
                pointService.addPoint(point);
                System.out.println(point.getId());
            }
            resp.getWriter().println("Тестовые данные внесены в БД");
        } else {
            resp.getWriter().println("Тестовые данные были добавлены ранее");
        }
    }

}
