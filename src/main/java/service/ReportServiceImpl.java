package service;

import dao.ReportDao;
import dao.ReportDaoImpl;
import dto.PointSimple;
import model.ModelPoint;
import model.Report;
import model.ReportUrnPoint;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SimpleTimeZone;

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
    public long createReport(List<PointSimple> urnPoints, List<ModelPoint> garbagePoints) {
        Report report = new Report(LocalDateTime.now());
        report.getGarbagePoints().addAll(garbagePoints);
        reportDao.addObj(report);
        for (PointSimple pointIn: urnPoints) {
            reportDao.addObj(new ReportUrnPoint(pointIn, report));
        }
        return report.getId();
    }

    @Override
    public List<PointSimple> getGarbagePointFromReport(Report report) {
        Set<ModelPoint> modelPoints = report.getGarbagePoints();
        List<PointSimple> points = new ArrayList<>();
        for (ModelPoint point : modelPoints) {
            points.add(new PointSimple(point));
        }
        return points;
    }

    @Override
    public List<PointSimple> getUrnPointFromReport(Report report) {
        List<ReportUrnPoint> pointsDB = reportDao.getPointsByReportID(report);
        List<PointSimple> points = new ArrayList<>();
        for (ReportUrnPoint pointDB: pointsDB) {
            points.add(new PointSimple(pointDB));
        }
        return points;
    }

    @Override
    public Report getReportById(long id) {
        return reportDao.getById(id, Report.class.getName());
    }



}
