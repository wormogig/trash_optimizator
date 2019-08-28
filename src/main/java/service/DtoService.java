package service;

import dto.*;

import java.util.List;

public interface DtoService {
    public List<UrnPoint> createListUrnPoint(double[][] preparedPoint);
    public PointInfo getPoint(long id);
    public PointInfoAdmin getPointAdmin(long id);
    public List<PointMarker> getPoints();
    public boolean addPoint(PointSend point);
}
