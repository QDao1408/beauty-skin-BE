package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "UserID",nullable = false)
    private User user;


    @Column(name = "ReceiverName")
    private String name;
    @Column(name = "ReceiverPhone")
    @Pattern(regexp = "(0[3|5|7|8|9])+([0-9]{8})\\b")
    private String phone;
    @Column(name = "Address")
    @NotNull
    private String address;
    @Column(name = "City")
    @NotNull
    private String province;
    @Column(name = "District")
    @NotNull
    private String district;
    @Column(name = "Ward")
    @NotNull
    private String ward;
    private boolean isDeleted = false;

    @OneToOne(mappedBy = "userAddress", cascade = CascadeType.ALL)
    @JsonIgnore
    private Order order;

    public UserAddress() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public String getWard() { return ward; }
    public void setWard(String ward) { this.ward = ward; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
