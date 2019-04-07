package org.nju.sesta.sams.response.personalInfo;

import javax.persistence.Embeddable;

@Embeddable
public class ContactInformation {
    private String phoneNumber;
    private String qqNumber;
    private String wcNumber;

    public ContactInformation(String phoneNumber, String qqNumber, String wcNumber) {
        this.phoneNumber = phoneNumber;
        this.qqNumber = qqNumber;
        this.wcNumber = wcNumber;
    }

    public ContactInformation() {
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public void setWcNumber(String wcNumber) {
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
