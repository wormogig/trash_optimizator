package dto;

import java.util.List;

public class CalcRequest {
    private int numUrn;
    private List<Long> pointIds;

    public int getNumUrn() {
        return numUrn;
    }

    public List<Long> getPointIds() {
        return pointIds;
    }
}
