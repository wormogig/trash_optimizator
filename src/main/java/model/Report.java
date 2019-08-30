package model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "reports")
public class Report extends AbstractDbObj {

    @Column(name = "date")
    private LocalDateTime dateTime;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable (
            name = "Report_ModelPoint",
            joinColumns = {@JoinColumn(name = "report_id")},
            inverseJoinColumns = {@JoinColumn(name = "modelpoint_id")})
    private Set<ModelPoint> garbagePoints = new HashSet<>();

    public Report() {
    }

    public Report(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Set<ModelPoint> getGarbagePoints() {
        return garbagePoints;
    }
}
