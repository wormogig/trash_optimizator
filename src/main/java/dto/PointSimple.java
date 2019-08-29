package dto;

import model.ModelPoint;
import model.ReportUrnPoint;

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

    public PointSimple(ReportUrnPoint point) {
        this.lat = point.getLat();
        this.lng = point.getLng();
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
