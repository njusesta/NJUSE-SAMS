package org.nju.sesta.sams.parameter.password;

public class PasswordResetParameter {
    private String code;
    private String newPassword;

    public String getCode() {
        return code;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public PasswordResetParameter(String code, String newPassword) {
        this.code = code;
        this.newPassword = newPassword;
    }
}
