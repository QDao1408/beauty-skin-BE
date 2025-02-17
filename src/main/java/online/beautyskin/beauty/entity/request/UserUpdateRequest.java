package online.beautyskin.beauty.entity.request;

import online.beautyskin.beauty.enums.GenderEnums;

import java.time.LocalDateTime;

public class UserUpdateRequest {
    private String fullName;
    private String phone;
    private GenderEnums gender;
    private LocalDateTime birthday;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public GenderEnums getGender() {
        return gender;
    }

    public void setGender(GenderEnums gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public UserUpdateRequest() {
    }

    public UserUpdateRequest(String fullName, String phone, GenderEnums gender, LocalDateTime birthday) {
        this.fullName = fullName;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
    }
}
