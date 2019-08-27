package servlet.back;

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
        List<User> users = new ArrayList<>();
        users.add(new User("User1"));
        users.add(new User("User2"));
        users.add(new User("User3"));
        for (User user: users) {
            userService.addUser(user);
            System.out.println(user.getId());
        }
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Мусор", "Описание"));
        categories.add(new Category("small trash", "Описание"));
        for (Category category: categories) {
            categoryService.addCategory(category);
            System.out.println(category.getId());
        }

        List<ModelPoint> points = new ArrayList<>();
        points.add(new ModelPoint(60.775243, 28.698896, categories.get(0), "12/04/1980 13:01:20", users.get(0),false));
        points.add(new ModelPoint(60.774070, 28.698896, categories.get(0), "12/04/1980 13:01:20", users.get(1),false));
        points.add(new ModelPoint(60.774453, 28.697518, categories.get(1), "12/04/1980 13:01:20", users.get(2), false));
        points.add(new ModelPoint(60.774557, 28.696931, categories.get(0), "12/04/1980 13:01:20", users.get(0),false));
        points.add(new ModelPoint(60.775584, 28.697220, categories.get(0), "12/04/1980 13:01:20", users.get(1),false));
        points.add(new ModelPoint(60.775846, 28.696630, categories.get(1), "12/04/1980 13:01:20", users.get(2), false));

        for (ModelPoint point: points) {
            pointService.addPoint(point);
            System.out.println(point.getId());
        }
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println("Тестовые данные внесены в БД");
    }

}
