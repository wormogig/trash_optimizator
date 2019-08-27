package service;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;
    private UserDao userDao;

    private UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            synchronized (PointServiceImpl.class) {
                if (instance==null){
                    instance = new UserServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addObj(user);
    }

    @Override
    public User getUserByID(long id) {
        return userDao.getUserByID(id);
    }
}
