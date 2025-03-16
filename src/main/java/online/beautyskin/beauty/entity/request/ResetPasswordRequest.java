package online.beautyskin.beauty.entity.request;


public class ResetPasswordRequest {
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
