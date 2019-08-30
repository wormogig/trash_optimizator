package service;

public class ClearDBService {
    public static boolean clearAllTable() {
        return  CategoryServiceImpl.getInstance().deleteAllCategories() &&
                UserServiceImpl.getInstance().deleteAllUser() &&
                PointServiceImpl.getInstance().deleteAllPoints() &&
                ReportServiceImpl.getInstance().deleteAllReports();
    }
}
