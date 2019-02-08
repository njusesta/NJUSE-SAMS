package org.nju.sesta.sams.service;

import org.nju.sesta.sams.dao.ActivityRepository;
import org.nju.sesta.sams.factory.ActivityFactory;
import org.nju.sesta.sams.parameter.activity.NewMatchParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityManageService {
    @Autowired
    ActivityRepository repository;

    public boolean applyForNewMatch(NewMatchParameter parameter){
        int rowsAffected = repository.saveActivity(ActivityFactory.create(parameter));
        return rowsAffected !=0;
    }
}
