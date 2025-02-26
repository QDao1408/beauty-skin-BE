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

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<MappingProductImage> mappingProductImages = new ArrayList<>();

    public void addMappingProductImage(MappingProductImage mappingProductImage) {
        this.mappingProductImages.add(mappingProductImage);
        mappingProductImage.setImage(this);
    }
    public void removeMappingProductImage(MappingProductImage mappingProductImage) {
        this.mappingProductImages.remove(mappingProductImage);
        mappingProductImage.setImage(null);
    }

    public Image() {}
    public Image(String url) { this.url = url; }
    public void setId(long id) { this.id = id; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
    public List<MappingProductImage> getMappingProductImages() {return mappingProductImages; }
    public void setMappingProductImages(List<MappingProductImage> mappingProductImages) { this.mappingProductImages = mappingProductImages; }
    public long getId() { return this.id; }
}
