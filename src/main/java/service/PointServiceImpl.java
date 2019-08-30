package service;

import dao.PointDao;
import dao.PointDaoImpl;
import dto.PointSimple;
import model.ModelPoint;


import java.util.ArrayList;
import java.util.List;

public class PointServiceImpl implements PointService {
    private static PointServiceImpl instance;
    private PointDao pointDAO;

    private PointServiceImpl() {
        pointDAO = new PointDaoImpl();
    }

    public static PointServiceImpl getInstance() {
        if (instance == null) {
            synchronized (PointServiceImpl.class) {
                if (instance==null){
                    instance = new PointServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public double[][] preparePointsArray(List<ModelPoint> points) {
        double[][] readyPoints = new double[points.size()][2];
        for (int i = 0; i < points.size(); i++) {
            readyPoints[i][0] = points.get(i).getLatitude();
            readyPoints[i][1] = points.get(i).getLongitude();
        }
        return readyPoints;
    }

    @Override
    public ModelPoint getPoint(long id) {
        return pointDAO.getPoint(id);
    }

    @Override
    public List<ModelPoint> getPoints() {
        return pointDAO.getPoints();
    }

    @Override
    public List<ModelPoint> getPointsById(List<Long> ids) {
        List<ModelPoint> points = new ArrayList<>();
        for (Long id :ids) {
            points.add(getPoint(id));
        }
        return points;
    }

    @Override
    public boolean addPoint(ModelPoint point) {
        return pointDAO.addObj(point);
    }

    @Override
    public boolean deleteAllPoints() {
        return pointDAO.deleteAll(ModelPoint.class.getName());
    }

    @Override
    public boolean deletePointById(long id) {
        return pointDAO.deletePointByID(id);
    }

    @Override
    public List<PointSimple> convertPointType(List<ModelPoint> pointsIn) {
        List<PointSimple> points = new ArrayList<>();
        for (ModelPoint pointIn : pointsIn) {
            points.add(new PointSimple(pointIn));
        }
        return points;
    }
}
