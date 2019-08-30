package dto;

import model.ModelPoint;

public class PointInfoAdmin extends PointInfo{
    private long id;

    public PointInfoAdmin(ModelPoint point) {
        super(point);
        id = point.getId();
    }
}
