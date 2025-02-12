package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import online.beautyskin.beauty.enums.RoleEnums;


@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;

    @Column(name = "Full_Name")
    private String fullName;

    @Column(name = "Phone")
    //@Pattern(regexp = "(0[3|5|7|8|9])+([0-9]{8})\\b")
    private String phone;

    @Column(name = "Skin_Type")
    private String skinType;

    @Column(name = "Address")
    private String address;

    @Column(name = "Username", unique = true)
    private String username;

    @Column(name = "Password")
    //@Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@$#*!%&]).{10,}$")
    private String password;

    @Column(name = "Mail")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String mail;

    private boolean isDeleted = false;

    @Enumerated(value = EnumType.STRING)
    public RoleEnums roleEnums;

    public User(){}

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public @Pattern(regexp = "(0[3|5|7|8|9])+([0-9]{8})\\b") String getPhone() {
        return phone;
    }

    public void setPhone(@Pattern(regexp = "(0[3|5|7|8|9])+([0-9]{8})\\b") String phone) {
        this.phone = phone;
    }

    public String getSkinType() {
        return skinType;
    }

    public void setSkinType(String skinType) {
        this.skinType = skinType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@$#*!%&]).{10,}$") String getPassword() {
        return password;
    }

    public void setPassword(@Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@$#*!%&]).{10,}$") String password) {
        this.password = password;
    }

    public @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") String getMail() {
        return mail;
    }

    public void setMail(@Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") String mail) {
        this.mail = mail;
    }
}
