package dto;

import model.ModelPoint;

public class PointInfoAdmin extends PointInfo{
    private long id;
    private String image;

    public PointInfoAdmin(ModelPoint point) {
        super(point);
        id = point.getId();
        image = point.getImage();
    }
}
