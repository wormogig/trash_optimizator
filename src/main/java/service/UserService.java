package service;

import model.User;

public interface UserService {
    public boolean addUser(User user);
    public boolean deleteAllUser();
    public User getUserByID(long id);
}
