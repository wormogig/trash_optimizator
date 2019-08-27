package dao;

import model.AbstractDbObj;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

public abstract class AbstractDaoImpl implements AbstractDao{

    SessionFactory sessionFactory;

    public AbstractDaoImpl() {
        sessionFactory = DBHelper.getSessionFactory();
    }

    public boolean addObj(AbstractDbObj obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        boolean isAdded = false;
        try {
            session.save(obj);
            transaction.commit();
            isAdded = true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return isAdded;
    }

    @Override
    public <T extends AbstractDbObj> T getById(long id, String className) {
        Session session = sessionFactory.openSession();
        T dbObj = null;
        try {
            Query query = session.createQuery("FROM " + className + " t WHERE t.id = :id");
            query.setParameter("id", id);
            dbObj = (T) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dbObj;
    }
}
