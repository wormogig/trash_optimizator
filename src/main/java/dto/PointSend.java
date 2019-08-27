package dto;

import java.time.LocalDateTime;

public class PointSend {
    private long userId;
    private long categoryId;
    private double lat;
    private double lng;
//    private LocalDateTime date;
    private String date;

    public PointSend() {
    }

    public PointSend(long userId, long categoryId, double latitude, double longitude, String date) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.lat = latitude;
        this.lng = longitude;
        this.date = date;
    }

    public long getUserId() {
        return userId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getDate() {
        return date;
    }

    //    public LocalDateTime getDate() {
//        return date;
//    }
}
