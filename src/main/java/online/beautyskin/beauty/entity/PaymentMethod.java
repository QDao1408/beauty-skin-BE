package online.beautyskin.beauty.entity;

import jakarta.persistence.*;

@Entity
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="Name")
    private String name;
    private boolean isDeleted;

    @OneToOne(mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    private Order order;

    public PaymentMethod() {

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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
