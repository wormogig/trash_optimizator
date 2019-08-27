package service;

import dto.PointInfo;
import dto.PointMarker;
import dto.PointSend;
import dto.UrnPoint;

import java.util.List;

public interface DtoService {
    public List<UrnPoint> createListUrnPoint(double[][] preparedPoint);
    public PointInfo getPoint(long id);
    public List<PointMarker> getPoints();
    public boolean addPoint(PointSend point);
}
