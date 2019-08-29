package util;

import model.Category;
import model.ModelPoint;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    private ArrayList<User> users;
    private ArrayList<Category> categories;

    public ArrayList<User> generateUsers(int numUsers) {
        this.users = new ArrayList<>();
        for (int i = 1; i <= numUsers; i++) {
            users.add(new User("User" + i));
        }
        return users;
    }

    public ArrayList<Category> generateCategories() {
        this.categories = new ArrayList<>();

        categories.add(new Category("Мелкий мусор", "Немного мусора вне урны"));
        categories.add(new Category("Куча мусора", "Большое количествоо скопившегося мусора"));
        categories.add(new Category("Свалка", "Огромная куча бытовых или строительных отходов"));

        return categories;
    }

    public ArrayList<ModelPoint> generatePoints(int numPoints) {
        ArrayList<ModelPoint> points = new ArrayList<>();

        for (int i = 1; i <= numPoints; i++) {
            double lat = 60.707046 + Math.random() / 150;
            double lng = 28.757255 + Math.random() / 75;
            String date = generateDate();

            int randCategoryId = (int)(Math.random()*categories.size());
            int randUserId = (int)(Math.random()*users.size());

            points.add(new ModelPoint(lat, lng, categories.get(randCategoryId), date, users.get(randUserId), false, ""));
        }

        return points;
    }

    private String generateDate() {
        String date;

        int day = 10 + (int) (Math.random() * 28);
        int month = (int) (Math.random() * 9);
        int year = 2019;

        int hours = 10 + (int) (Math.random() * 13);
        int minutes = 10 + (int) (Math.random() * 49);
        int seconds = 10 + (int) (Math.random() * 49);

        date = "" + day + "/0" + month + "/" + year + " " + hours + ":" + minutes + ":" + seconds;
        return date;
    }


}

