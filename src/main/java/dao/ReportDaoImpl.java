package dao;

import model.Report;
import model.ReportUrnPoint;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ReportDaoImpl extends AbstractDaoImpl implements ReportDao {

    @Override
    public List<ReportUrnPoint> getPointsByReportID(Report report) {
        Session session = sessionFactory.openSession();
        List<ReportUrnPoint> points = new ArrayList<>();
        try {
            Query query = session.createQuery("FROM ReportUrnPoint ru WHERE ru.report = :report");
            query.setParameter("report", report);
            points = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return points;
    }
}
