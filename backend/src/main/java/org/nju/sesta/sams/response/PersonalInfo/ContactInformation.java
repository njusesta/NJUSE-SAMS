package org.nju.sesta.sams.response.PersonalInfo;

public class ContactInformation {
    private String phoneNumber;
    private String qqNumber;
    private String wcNumber;

    public ContactInformation(String phoneNumber, String qqNumber, String wcNumber) {
        this.phoneNumber = phoneNumber;
        this.qqNumber = qqNumber;
        this.wcNumber = wcNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public String getWcNumber() {
        return wcNumber;
    }
}
