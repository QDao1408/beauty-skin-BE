package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    @Column(name = "ReceiverName")
    private String receiverName;
    @Column(name = "ReceiverPhone")
    @Pattern(regexp = "(0[3|5|7|8|9])+([0-9]{8})\\b")
    private String receiverPhone;
    @Column(name = "Address")
    @NotNull
    private String receiverAddress;
    @Column(name = "City")
    @NotNull
    private String city;
    @Column(name = "District")
    @NotNull
    private String district;
    @Column(name = "Ward")
    @NotNull
    private String ward;
    private boolean isDeleted = false;

    public UserAddress() {}
    public UserAddress(String receiverName, String receiverPhone, String receiverAddress, String city, String district, String ward) {
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverAddress = receiverAddress;
        this.city = city;
        this.district = district;
        this.ward = ward;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    public String getReceiverPhone() { return receiverPhone; }
    public void setReceiverPhone(String receiverPhone) { this.receiverPhone = receiverPhone; }
    public String getReceiverAddress() { return receiverAddress; }
    public void setReceiverAddress(String receiverAddress) { this.receiverAddress = receiverAddress; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public String getWard() { return ward; }
    public void setWard(String ward) { this.ward = ward; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }

}
