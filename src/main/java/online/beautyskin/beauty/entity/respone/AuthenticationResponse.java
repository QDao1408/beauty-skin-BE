package online.beautyskin.beauty.entity.respone;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import online.beautyskin.beauty.enums.RoleEnums;

public class AuthenticationResponse {
    private long id;
    private String mail;
    private String username;
    private String password;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private RoleEnums roleEnum;
    private String token;
    private boolean isDeleted;

    private boolean isActive;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(long id, String mail, String username, String password, RoleEnums roleEnum, String token, boolean isDeleted, boolean isActive) {
        this.id = id;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.roleEnum = roleEnum;
        this.token = token;
        this.isDeleted = isDeleted;
        this.isActive = isActive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnums getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnums roleEnum) {
        this.roleEnum = roleEnum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
