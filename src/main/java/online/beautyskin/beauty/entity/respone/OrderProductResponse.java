package online.beautyskin.beauty.entity.respone;


import online.beautyskin.beauty.entity.Category;
import online.beautyskin.beauty.entity.Image;

public class OrderProductResponse {
    private long id;
    private String name;
    private double price;
    private Category category;
    private Image image;
    private double promotion;


    public OrderProductResponse() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public double getPromotion() {
        return promotion;
    }
    public void setPromotion(double promotion) {
        this.promotion = promotion;
    }


}
