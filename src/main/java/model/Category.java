package model;

import javax.persistence.*;

@Entity
@Table(name="category")
public class Category extends AbstractDbObj{

    @Column(name="title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private long image;

    public Category() {
    }

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
}
