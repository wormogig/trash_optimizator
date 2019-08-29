package service;

import dto.PointSimple;

import java.util.List;

public interface ReportService {
    long createReport(List<PointSimple> pointsIn);
    List<PointSimple> getPointFromReport(long reportsId);
}
