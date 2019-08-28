package dto;

import model.ModelPoint;

public class PointSimple {
    private double lat;
    private double lng;

    public PointSimple(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public PointSimple(ModelPoint point) {
        this.lat = point.getLatitude();
        this.lng = point.getLongitude();
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
