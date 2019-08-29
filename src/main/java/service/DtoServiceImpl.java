package service;

import dto.*;
import model.Category;
import model.ModelPoint;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class DtoServiceImpl implements DtoService {
    private static DtoServiceImpl instance;
    private PointService pointService = PointServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();
    private CategoryService categoryService = CategoryServiceImpl.getInstance();

    private DtoServiceImpl() {

    }

    public static DtoServiceImpl getInstance() {
        if (instance == null) {
            synchronized (DtoServiceImpl.class) {
                if (instance==null){
                    instance = new DtoServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<UrnPoint> createListUrnPoint(double[][] preparedPoint) {
        List<UrnPoint> urnPoints = new ArrayList<>();
        for (int i = 0; i < preparedPoint.length; i++) {
            urnPoints.add(new UrnPoint(preparedPoint[i][0], preparedPoint[i][1]));
        }
        return urnPoints;
    }

    @Override
    public PointInfo getPoint(long id) {
        ModelPoint modelPoint = pointService.getPoint(id);
        PointInfo point = null;
        if (modelPoint != null) {
            point = new PointInfo(modelPoint);
        }
        return point;
    }

    @Override
    public PointInfoAdmin getPointAdmin(long id) {
        ModelPoint modelPoint = pointService.getPoint(id);
        PointInfoAdmin point = null;
        if (modelPoint != null) {
            point = new PointInfoAdmin(modelPoint);
        }
        return point;
    }

    @Override
    public List<PointMarker> getPoints() {
//        Здесь вылетает NPE если база пустая!!! Но это не точно. Но один раз это вылетело. Михаил
        List<PointMarker> pointMarkers = new ArrayList<>();
        List<ModelPoint> points = pointService.getPoints();
        for (ModelPoint point:points) {
            pointMarkers.add(new PointMarker(point));
        }
        return pointMarkers;
    }

    @Override
    public boolean addPoint(PointSend point) {
        User user = userService.getUserByID(point.getUserId());
        Category category = categoryService.getCategoryByID(point.getCategoryId());
        return pointService.addPoint(new ModelPoint(
                point.getLat(),
                point.getLng(),
                category,
                point.getDate(),
                user,
                false,
                point.getImage()));
    }
}
