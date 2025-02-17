package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import online.beautyskin.beauty.enums.GenderEnums;
import online.beautyskin.beauty.enums.RoleEnums;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity

public class User implements UserDetails {
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
    @NotNull(message = "Username cannot be null.")
    private String username;

    @Column(name = "Password")
    //@Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@$#*!%&]).{10,}$")
    private String password;

    @Column(name = "Mail", unique = true)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String mail;

    @Column(name = "Gender")
    private GenderEnums gender;

    @Column(name = "Birthday")
    private LocalDateTime birthday;

    @Column(name = "IsDeleted")
    private boolean isDeleted = false;

    @Column(name = "IsUpdate")
    private boolean isActive = true;

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

    public RoleEnums getRoleEnums() {
        return roleEnums;
    }

    public void setRoleEnums(RoleEnums roleEnums) {
        this.roleEnums = roleEnums;
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

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = SimpleGrantedAuthority.class, name = "SimpleGrantedAuthority")
    })
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.roleEnums.toString()));
        return authorities;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
