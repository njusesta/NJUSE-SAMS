package org.nju.sesta.sams.response.PersonalInfo;

import org.nju.sesta.sams.entity.Activity;
import org.nju.sesta.sams.entity.Role;
import org.nju.sesta.sams.entity.User;
import org.nju.sesta.sams.enums.RoleName;

import java.util.List;
import java.util.stream.Collectors;

public class PersonalInfoResponse {
    private String id;
    private String name;
    private String clazz;
    private List<String> roles;
    private ContactInformation contactInfo;
    private List<Activity> activitiesJoined;
    private List<Activity> activitiesReleased;

    public PersonalInfoResponse(User user) {
        id = user.getId();
        name = user.getName();
        clazz = user.getClazz();
        roles = mapToRoleName(user.getRoles());
        contactInfo = user.getContactInformation();
        activitiesJoined = user.getActivitiesJoined();
        activitiesReleased = user.getActivitiesReleased();
    }

    public List<String> mapToRoleName(List<Role> roles) {
        return roles.stream()
                .map(role -> role.getRoleName().name())
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public ContactInformation getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInformation contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Activity> getActivitiesJoined() {
        return activitiesJoined;
    }

    public void setActivitiesJoined(List<Activity> activitiesJoined) {
        this.activitiesJoined = activitiesJoined;
    }

    public List<Activity> getActivitiesReleased() {
        return activitiesReleased;
    }

    public void setActivitiesReleased(List<Activity> activitiesReleased) {
        this.activitiesReleased = activitiesReleased;
    }
}
