package org.nju.sesta.sams.service;

import org.nju.sesta.sams.dao.ActivityRepository;
import org.nju.sesta.sams.dao.ApplicantRepository;
import org.nju.sesta.sams.dao.UserRepository;
import org.nju.sesta.sams.entity.Activity;
import org.nju.sesta.sams.entity.Applicant;
import org.nju.sesta.sams.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ActivityManageService {
    @Autowired
    ActivityRepository activityRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    ApplicantRepository applicantRepo;

    public boolean applyForNewMatch(Activity activity, String studentId) {
        User user = userRepo.getOne(studentId);
        user.getActivitiesReleased().add(activity);
        userRepo.save(user);
        return true;
    }

    public boolean applyForNewActivity(Activity activity, String studentId) {
        User user = userRepo.getOne(studentId);
        user.getActivitiesReleased().add(activity);
        userRepo.save(user);
        return true;
    }

    public boolean applyForNewRecruitment(Activity activity, String studentId) {
        User user = userRepo.getOne(studentId);
        user.getActivitiesReleased().add(activity);
        userRepo.save(user);
        return true;
    }

    public boolean updateActivity(Activity activity) {
        activityRepo.save(activity);
        return true;
    }

    public Activity[] getActivityList() {
        return activityRepo.findAll(Sort.by(Sort.Direction.DESC, "initDate")).stream().toArray(Activity[]::new);
    }


    public Activity getActivityDetail(long id) {
        return activityRepo.getOne(id);
    }

    public boolean signUpForActivity(Long id, String studentId) {
        User u = userRepo.getOne(studentId);
        Activity a = activityRepo.getOne(id);
        u.getActivitiesJoined().add(a);
        userRepo.save(u);
        return true;
    }

    public boolean matchActivityAndUser(Long activityId, String studentId) {
        Activity activity = activityRepo.getOne(activityId);
        return activity.getCreator().getId() == studentId;
    }

    public Applicant[] getApplicationForm(Long activityId) {
        return (Applicant[]) applicantRepo.findAllByActivityId(activityId).toArray();
    }

    public boolean sendApplicationForm(Long activityId, String description, String studentId) {
        Applicant applicant = new Applicant();
        applicant.setActivityId(activityId);
        applicant.setDescription(description);
        applicant.setStudentId(studentId);
        applicantRepo.save(applicant);
        return true;
    }
}
