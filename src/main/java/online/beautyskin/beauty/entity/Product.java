package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.service.annotation.GetExchange;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Product_Name")
    private String name;

    @Column(name = "Form")
    private String form; // liquid, solid, powder, wasp

    @Column(name = "Description")
    private String description;

    @Column(name = "Origin")
    private String origin;

    @Column(name = "Image")
    private String image;

    @Column(name = "Price")
    @Min(value = 0, message = "Price must greater than 0")
    private int price;

    @Column(name = "Quantity")
    @Min(value = 0, message = "Quantity must greater than 0")
    private int quantity;

    @Column(name = "Category")
    private String category;

    @Column(name = "Capacity")
    private int capacity;

    boolean isDeleted = false;

    public Product() {}

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
