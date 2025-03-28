package online.beautyskin.beauty.entity.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import online.beautyskin.beauty.entity.Image;

public class ReportRequest {

    private String description;
    private String reason;
    private long orderId;
    private List<ImageRequest> images = new ArrayList<>();
    public ReportRequest() {
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public long getOrderId() {
        return orderId;
    }
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    public List<ImageRequest> getImages() {
        return images;
    }
    public void setImages(List<ImageRequest> images) {
        this.images = images;
    }

    

}
