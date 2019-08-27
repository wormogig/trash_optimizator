package service;

import model.ModelPoint;

import java.util.List;

public interface PointService {
    public double[][] preparePointsArray(List<ModelPoint> points);
    public ModelPoint getPoint(long id);
    public List<ModelPoint> getPoints();
    public List<ModelPoint> getPointsById(List<Long> ids);
    public boolean addPoint(ModelPoint point);
    public boolean deletePointById(long id);
}
