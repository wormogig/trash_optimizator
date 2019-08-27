package model;



import dto.PointSend;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "point")
public class ModelPoint extends AbstractDbObj{

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @ManyToOne
    private Category category;

    @Column(name = "date")
    private String date;
//    private LocalDateTime date;

    @ManyToOne
    private User user;

    @Column
    private boolean isComleted;


    public ModelPoint() {
    }

    public ModelPoint(double latitude, double longitude, Category category, String date, User user, boolean isComleted) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.date = date;
        this.user = user;
        this.isComleted = isComleted;
    }

    public boolean isComleted() {
        return isComleted;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Category getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

//    public LocalDateTime getDate() {
//        return date;
//    }

    public User getUser() {
        return user;
    }
}
