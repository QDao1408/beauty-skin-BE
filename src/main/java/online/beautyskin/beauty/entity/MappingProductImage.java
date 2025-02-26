package online.beautyskin.beauty.entity;

import jakarta.persistence.*;

@Entity
public class MappingProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;
    @Column(name = "display_order")
    private int displayOrder;
    private boolean isMainImage;

    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public MappingProductImage() {}
    public MappingProductImage(int displayOrder, boolean isMainImage, Image image, Product product) {
        this.displayOrder = displayOrder;
        this.isMainImage = isMainImage;
        this.image = image;
        this.product = product;
    }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public int getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(int displayOrder) { this.displayOrder = displayOrder; }
    public boolean isMainImage() { return isMainImage; }
    public void setMainImage(boolean mainImage) { this.isMainImage = mainImage; }
    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}
