package dao;

import model.User;

public interface UserDao extends AbstractDao{
    public User getUserByID(long id);
}
