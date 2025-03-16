package online.beautyskin.beauty.entity.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;

public class FeedbackRequest {
    private Long OrderId;
    @Min(value = 0)
    @Max(value = 5)
    private float rating;
    private String comment;
    private String image;
    private LocalDate feedBackDate;

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
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
