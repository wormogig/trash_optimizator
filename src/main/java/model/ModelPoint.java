package model;



import javax.persistence.*;


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

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String image;

    @ManyToOne
    private User user;

    @Column
    private boolean isCompleted;


    public ModelPoint() {
    }


    public ModelPoint(double latitude, double longitude, Category category, String date, User user, boolean isCompleted, String image) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.date = date;
        this.user = user;
        this.isCompleted = isCompleted;
        this.image = image;
    }

    public boolean isCompleted() {
        return isCompleted;
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

    public String getImage() {
        return image;
    }
}
