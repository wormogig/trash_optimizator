package dto;

import java.time.LocalDateTime;

public class UrnPoint {

    private double lat;
    private double lng;
    private LocalDateTime time;

    public UrnPoint(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
        this.time = LocalDateTime.now();
    }
}
