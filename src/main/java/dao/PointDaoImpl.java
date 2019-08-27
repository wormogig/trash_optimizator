package dao;

import model.ModelPoint;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PointDaoImpl extends AbstractDaoImpl implements PointDao {

    @Override
    public ModelPoint getPoint(long id) {
        return super.getById(id, ModelPoint.class.getName());
    }

    @Override
    public List<ModelPoint> getPoints() {
        Session session = sessionFactory.openSession();
        List<ModelPoint> points = null;
        try {
            Query query = session.createQuery("FROM ModelPoint");
            points = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return points;
    }

    @Override
    public boolean deletePointByID(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        boolean isDeleted = false;
        try {
            Query query = session.createQuery("DELETE FROM ModelPoint mp WHERE mp.id = :pointId");
            query.setParameter("pointId", id);
            query.executeUpdate();
            isDeleted = true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return isDeleted;
    }

    @Override
    public boolean setComplited(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        boolean isUpdated = false;
        try {
            Query query = session.createQuery("UPDATE ModelPoint mp SET mp.isCompleted = true WHERE mp.id = :pointId");
            query.setParameter("pointId", id);
            query.executeUpdate();
            transaction.commit();
            isUpdated = true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return isUpdated;
    }
}
