package online.beautyskin.beauty.entity.request;


import jakarta.validation.constraints.Pattern;

public class ResetPasswordRequest {
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@$#*!%&]).{8,}$",  message =  "password cần có chữ hoa, chữ thường, số, ký tự db")
    String password;

    String confirmPassword;

    public ResetPasswordRequest() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
