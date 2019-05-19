package org.nju.sesta.sams.service;

import org.nju.sesta.sams.dao.ActivityRepository;
import org.nju.sesta.sams.dao.ApplicantRepository;
import org.nju.sesta.sams.dao.UserRepository;
import org.nju.sesta.sams.entity.Activity;
import org.nju.sesta.sams.entity.Applicant;
import org.nju.sesta.sams.entity.User;
import org.nju.sesta.sams.exception.BadStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ActivityManageService {
    @Autowired
    ActivityRepository activityRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    ApplicantRepository applicantRepo;

    public void applyForNewMatch(Activity activity, String studentId) {
        User user = userRepo.findById(studentId).get();
        user.getActivitiesReleased().add(activity);
        userRepo.save(user);
    }

    public void applyForNewActivity(Activity activity, String studentId) {
        User user = userRepo.findById(studentId).get();
        user.getActivitiesReleased().add(activity);
        userRepo.save(user);
    }

    public void applyForNewRecruitment(Activity activity, String studentId) {
        User user = userRepo.findById(studentId).get();
        user.getActivitiesReleased().add(activity);
        userRepo.save(user);
    }

    public void updateActivity(Activity activity) {
        activityRepo.save(activity);
    }

    public Activity[] getAvailableActivityList() {
        return activityRepo.findAll(Sort.by(Sort.Direction.DESC, "initDate"))
                .stream()
                .filter(Activity::getPassed)
                .toArray(Activity[]::new);
    }

    public Activity[] getActivityUnexamined() {
        return activityRepo.findAll(Sort.by(Sort.Direction.DESC, "initDate"))
                .stream()
                .filter(o -> o.getPassed() == null)
                .toArray(Activity[]::new);
    }


    public Activity getActivityDetail(long id) {
        return activityRepo.findById(id).get();
    }

    public void signUpForActivity(Long id, String studentId) {
        User u = userRepo.findById(studentId).get();
        Activity a = activityRepo.findById(id).get();
        if (u.getActivitiesJoined()
                .stream()
                .map(Activity::getId)
                .anyMatch(o -> o == id))
            throw new BadStateException();
        u.getActivitiesJoined().add(a);
        userRepo.save(u);
    }

    public void matchActivityAndUser(Long activityId, String studentId) {
        Activity activity = activityRepo.findById(activityId).get();
        if (!activity.getCreator().getId().equals(studentId))
            throw new BadStateException();
    }

    public void examineActivity(Long activityId, Boolean decision) {
        Activity activity = activityRepo.findById(activityId).get();
        activity.setPassed(decision);
        activityRepo.save(activity);

    }

    public Applicant[] getApplicationForm(Long activityId) {
        return applicantRepo.findAllByActivityId(activityId).stream().toArray(Applicant[]::new);
    }

    public Applicant getCertainApplicationForm(Long activityId, Long formId) {
        return applicantRepo.findById(formId).get();
    }

    public void sendApplicationForm(Long activityId, String description, String studentId) {
        Applicant applicant = new Applicant();
        applicant.setActivityId(activityId);
        applicant.setDescription(description);
        applicant.setStudentId(studentId);
        applicantRepo.save(applicant);
    }
}
