package online.beautyskin.beauty.entity.respone;

public class UserListResponse {
    private Long id;
    private String fullName;
    private String mail;
    private String phone;
    private Boolean isActive;
    private double totalExpenditures;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public double getTotalExpenditures() {
        return totalExpenditures;
    }

    public void setTotalExpenditures(double totalExpenditures) {
        this.totalExpenditures = totalExpenditures;
    }

    public UserListResponse(Long id, String fullName, String mail, String phone, Boolean isActive, double totalExpenditures) {
        this.id = id;
        this.fullName = fullName;
        this.mail = mail;
        this.phone = phone;
        this.isActive = isActive;
        this.totalExpenditures = totalExpenditures;
    }

    public UserListResponse() {
    }
}
