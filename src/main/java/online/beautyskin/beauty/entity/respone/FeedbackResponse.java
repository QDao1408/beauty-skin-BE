package online.beautyskin.beauty.entity.respone;

import online.beautyskin.beauty.entity.Image;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FeedbackResponse {
    private long id;
    private float rating;
    private String comment;
    private LocalDate feedbackDate;
    private List<Image> images = new ArrayList<>();
    private String userName;

    public FeedbackResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public LocalDate getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(LocalDate feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
