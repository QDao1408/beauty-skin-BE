package online.beautyskin.beauty.entity.respone;

import online.beautyskin.beauty.enums.RoleEnums;

public class StaffResponse {
    private Long id;
    private String fullName;
    private String mail;
    private String phone;
    private Boolean isActive;
    private long completedOrders;
    private RoleEnums role;

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

    public double getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(long completedOrders) {
        this.completedOrders = completedOrders;
    }

    public RoleEnums getRole() {
        return role;
    }

    public void setRole(RoleEnums role) {
        this.role = role;
    }

    public StaffResponse(Long id, String fullName, String mail, String phone, Boolean isActive, long completedOrders) {
        this.id = id;
        this.fullName = fullName;
        this.mail = mail;
        this.phone = phone;
        this.isActive = isActive;
        this.completedOrders = completedOrders;
    }

    public StaffResponse() {
    }
}
