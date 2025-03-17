package online.beautyskin.beauty.entity.request;

import jakarta.validation.constraints.Pattern;

public class UserRequest {
    private String username;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@$#*!%&]).{8,}$",  message =  "password cần có chữ hoa, chữ thường, số, ký tự db")
    private String password;
    private String confirmPassword;
    private String email;
    private String fullName;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
