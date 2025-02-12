package online.beautyskin.beauty.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "fullName is not blank!")
    private String name;
    @NotBlank(message = "address is not blank!")
    private String address;
    @Pattern(regexp = "^(0|84)(2(0[3-9]|1[0-689]|2[0-25-9]|3[2-9]|4[0-9]|5[124-9]|6[0369]|7[0-7]|8[0-9]|9[012346789])|3[2-9]|5[25689]|7[06-9]|8[0-9]|9[012346789])([0-9]{7})$",message = "phone number is not valid!")
    private String phone;
    @Pattern(regexp = "^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$",message = "email not valid")
    private String mail;
    @NotBlank(message = "userName is not blank!")
    private String userName;
    @NotBlank(message = "userName is not blank!")
    private String password;
    private boolean isDelete = false;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return name;
    }

    public void setFullName(String fullName) {
        this.name = fullName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Manager() {
    }

    public Manager(String address, String name, long id, String mail, String password, String phone, String userName) {
        this.address = address;
        this.name = name;
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
        this.userName = userName;
    }
}
