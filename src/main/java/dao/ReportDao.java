package dao;

import model.Report;
import model.ReportUrnPoint;

import java.util.List;

public interface ReportDao extends AbstractDao {
    List<ReportUrnPoint> getPointsByReportID(long reportID);
}
