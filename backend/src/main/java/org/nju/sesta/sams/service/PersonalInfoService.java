package org.nju.sesta.sams.service;

import org.nju.sesta.sams.dao.UserRepository;
import org.nju.sesta.sams.entity.User;
import org.nju.sesta.sams.parameter.PersonalInfo.ActivityInfoParameter;
import org.nju.sesta.sams.parameter.PersonalInfo.BasicInfoParameter;
import org.nju.sesta.sams.parameter.PersonalInfo.DevFormInfoParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalInfoService {
    @Autowired
    UserRepository repository;

    public User getPersonalInfo(String id) {
        return repository.findByEmail(id);
    }

    public boolean updateBasicInfo(String id, BasicInfoParameter parameter) {
        int rowsAffected = repository.updateBasicInfo(parameter.getName(), parameter.getContactInformation(), parameter.getClazz(), id);
        return rowsAffected != 0;
    }

    public boolean updateActivityInfo(String id, ActivityInfoParameter parameter) {
        User user = repository.getOne(id);
        user.setActivitiesJoined(parameter.getActivities());
        repository.save(user);
//        int rowsAffected =repository.updateActivityInfo(parameter.getActivities(), id);
        return true;
    }

    public boolean updateDevAxFormInfo(String id, DevFormInfoParameter parameter) {
        User user = repository.getOne(id);
        user.setDevAxForm(parameter.getDevAxForm());
        repository.save(user);
//        int rowsAffected = repository.updateDevAxFormInfo(parameter.getDevAxForm(), id);
        return true;
    }

    public boolean applyForAuthorityUpdating(String targetAuthority, String id){
        return true;
    }


}
