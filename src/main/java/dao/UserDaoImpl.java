package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;

public class UserDaoImpl extends AbstractDaoImpl implements UserDao {

    @Override
    public User getUserByID(long id) {
        return super.getById(id, User.class.getName());
    }
}
