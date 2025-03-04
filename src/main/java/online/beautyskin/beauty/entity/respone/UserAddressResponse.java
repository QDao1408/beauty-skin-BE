package online.beautyskin.beauty.entity.respone;

public class UserAddressResponse {
    private long id;
    private String city;
    private String district;
    private String address;
    private String name;
    private String phone;
    private String ward;

    public UserAddressResponse() {
    }

    public UserAddressResponse(long id, String city, String district, String address, String name, String phone, String ward) {
        this.id = id;
        this.city = city;
        this.district = district;
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.ward = ward;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }
}
