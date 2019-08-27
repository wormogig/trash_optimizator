package model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends AbstractDbObj{

    @Column(name = "username")
    private String userName;

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}
