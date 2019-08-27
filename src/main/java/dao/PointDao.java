package dao;

import model.ModelPoint;

import java.util.List;

public interface PointDao extends AbstractDao{
    public ModelPoint getPoint(long id);
    public List<ModelPoint> getPoints();
    public boolean deletePointByID(long id);
    public boolean setComplited(long id);
}
