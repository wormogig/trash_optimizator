package dto;

import model.ModelPoint;

import java.time.LocalDateTime;

public class PointInfo {
    private String userName;
    private String categoryTitle;
//    private LocalDateTime date;
    private String date;
    private String image;

    public PointInfo(ModelPoint point) {
        userName = point.getUser().getUserName();
        categoryTitle = point.getCategory().getTitle();
        date = point.getDate();
        image = point.getImage();
    }
}
