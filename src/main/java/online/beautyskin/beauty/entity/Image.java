package online.beautyskin.beauty.entity;

import jakarta.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    @Column(name = "URL")
    private String url;
    private boolean isDeleted = false;

    public Image() {}
    public Image(String url) { this.url = url; }
    public void setId(long id) { this.id = id; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
}
