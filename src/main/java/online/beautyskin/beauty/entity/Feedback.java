package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import online.beautyskin.beauty.enums.FeedbackEnums;

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
    private String image;
    private boolean isDelete = false;
    @Enumerated(value = EnumType.STRING)

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }


    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) { this.product = product; }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Feedback() {
    }

}
