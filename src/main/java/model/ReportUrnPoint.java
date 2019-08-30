package model;

import dto.PointSimple;

import javax.persistence.*;

@Entity
@Table(name = "urn_points")
public class ReportUrnPoint extends AbstractDbObj{

    @Column(name = "lat")
    private double lat;

    @Column(name = "lng")
    private double lng;

    @ManyToOne
    private Report report;

    public ReportUrnPoint() {
    }

    public ReportUrnPoint(PointSimple point, Report report) {
        this.lat = point.getLat();
        this.lng = point.getLng();
        this.report = report;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
