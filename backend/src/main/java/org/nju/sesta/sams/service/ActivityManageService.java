package org.nju.sesta.sams.service;

import org.nju.sesta.sams.dao.ActivityRepository;
import org.nju.sesta.sams.entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityManageService {
    @Autowired
    ActivityRepository repository;

    public boolean applyForNewMatch(Activity activity) {
        Activity a = repository.save(activity);
        return true;
    }

    public boolean applyForNewActivity(Activity activity) {
        Activity a = repository.save(activity);
        return true;
    }
    public boolean signUpActivity(){

    }
    public Activity getActivityInfo(long id) {
        return repository.findById(id);
    }
}
