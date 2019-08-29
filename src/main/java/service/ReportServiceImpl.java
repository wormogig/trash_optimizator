package service;

import dao.ReportDao;
import dao.ReportDaoImpl;
import dto.PointSimple;
import model.Report;
import model.ReportUrnPoint;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReportServiceImpl implements ReportService {
    private static ReportService instance;
    private ReportDao reportDao;

    private ReportServiceImpl() {
        reportDao = new ReportDaoImpl();
    }

    public static ReportService getInstance() {
        if (instance == null) {
            synchronized (ReportServiceImpl.class) {
                if (instance==null){
                    instance = new ReportServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public long createReport(List<PointSimple> pointsIn) {
        Report report = new Report(LocalDateTime.now());
        reportDao.addObj(report);
        for (PointSimple pointIn: pointsIn) {
            reportDao.addObj(new ReportUrnPoint(pointIn, report));
        }
        return report.getId();
    }

    @Override
    public List<PointSimple> getPointFromReport(long reportsId) {
        Report report = reportDao.getById(reportsId, Report.class.getName());
        List<ReportUrnPoint> pointsDB = reportDao.getPointsByReportID(report);
        List<PointSimple> points = new ArrayList<>();
        for (ReportUrnPoint pointDB: pointsDB) {
            points.add(new PointSimple(pointDB));
        }
        return points;
    }
}
