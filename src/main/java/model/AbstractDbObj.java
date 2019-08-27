package model;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractDbObj {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }
}
