package org.nju.sesta.sams.parameter.PersonalInfo;

import org.nju.sesta.sams.response.PersonalInfo.ContactInformation;

public class BasicInfoParameter {
    private String name;
    private ContactInformation contactInformation;
    private String clazz;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
