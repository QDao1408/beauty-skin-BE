package online.beautyskin.beauty.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Min(value = 0)
    @Max(value = 5)
    private float rating;
    private String comment;
    private LocalDate feedBackDate;
    private boolean isDelete = false;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Feedback() {
    }

    public Feedback(String comment, LocalDate feedBackDate, long id, boolean isDelete, float rating) {
        this.comment = comment;
        this.feedBackDate = feedBackDate;
        this.id = id;
        this.isDelete = isDelete;
        this.rating = rating;
    }
}
