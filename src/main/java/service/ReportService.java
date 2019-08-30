package service;

import dto.PointSimple;
import model.ModelPoint;
import model.Report;

import java.util.List;

public interface ReportService {
    long createReport(List<PointSimple> pointsIn, List<ModelPoint> garbagePoints);
    List<PointSimple> getGarbagePointFromReport(Report report);
    Report getReportById(long id);
    List<PointSimple> getUrnPointFromReport(Report report);

}
