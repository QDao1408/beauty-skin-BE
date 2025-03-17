package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;



    @Column(name = "URL")
    private String url;
    private boolean isDeleted = false;

    @ManyToMany(mappedBy = "images", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<Product> products = new ArrayList<Product>();

    @ManyToOne
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    public Image() {}
    public Image(String url) { this.url = url; }
    public void setId(long id) { this.id = id; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
    public long getId() { return this.id; }

    public List<Product> getProducts() {
        return products;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
