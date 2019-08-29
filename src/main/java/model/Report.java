package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report extends AbstractDbObj {

    @Column(name = "date")
    private LocalDateTime dateTime;

    public Report() {
    }

    public Report(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


}
