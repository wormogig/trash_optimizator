package dto;

import model.ModelPoint;

public class PointMarker {

    private long id;
    private double lat;
    private double lng;
    private boolean isCompleted;

    public PointMarker(ModelPoint point) {
        id = point.getId();
        lat = point.getLatitude();
        lng = point.getLongitude();
        isCompleted = point.isCompleted();
    }
}
