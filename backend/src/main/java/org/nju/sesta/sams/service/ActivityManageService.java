package org.nju.sesta.sams.service;

import org.nju.sesta.sams.dao.ActivityRepository;
import org.nju.sesta.sams.dao.UserRepository;
import org.nju.sesta.sams.entity.Activity;
import org.nju.sesta.sams.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityManageService {
    @Autowired
    ActivityRepository activityDao;

    @Autowired
    UserRepository userDao;

    public boolean applyForNewMatch(Activity activity, String studentId) {
        User user = userDao.getOne(studentId);
        user.getActivitiesReleased().add(activity);
        userDao.save(user);
        return true;
    }

    public boolean applyForNewActivity(Activity activity, String studentId) {
        User user = userDao.getOne(studentId);
        user.getActivitiesReleased().add(activity);
        userDao.save(user);
        return true;
    }
    public boolean signUpActivity(Long id, String studentId){
        User u =userDao.getOne(studentId);
        Activity a = activityDao.getOne(id);
        u.getActivitiesJoined().add(a);
        userDao.save(u);
        return true;
    }

    public Activity getActivityInfo(long id) {
        return activityDao.findById(id);
    }
}
