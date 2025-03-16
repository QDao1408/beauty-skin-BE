package online.beautyskin.beauty.entity.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;

public class FeedbackRequest {
    private Long OrderDetailId;
    @Min(value = 0)
    @Max(value = 5)
    private float rating;
    private String comment;
    private String image;
    private LocalDate feedBackDate;

    public Long getOrderDetailId() {
        return OrderDetailId;
    }

    public void setOrderDetailId(Long orderId) {
        OrderDetailId = orderId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getFeedBackDate() {
        return feedBackDate;
    }

    public void setFeedBackDate(LocalDate feedBackDate) {
        this.feedBackDate = feedBackDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
