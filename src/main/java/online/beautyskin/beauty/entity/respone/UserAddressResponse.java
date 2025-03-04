package online.beautyskin.beauty.entity.respone;

public class UserAddressResponse {
    private String name;
    private String phone;
    private String address;
    private long id;

    public UserAddressResponse() {}

    public UserAddressResponse(String name, String phone, String address, long id) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
