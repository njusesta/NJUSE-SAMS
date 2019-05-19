package org.nju.sesta.sams.service;

import org.nju.sesta.sams.dao.AuthUpReqRepository;
import org.nju.sesta.sams.dao.MessageRepository;
import org.nju.sesta.sams.dao.RoleRepository;
import org.nju.sesta.sams.dao.UserRepository;
import org.nju.sesta.sams.entity.*;
import org.nju.sesta.sams.enums.RoleName;
import org.nju.sesta.sams.parameter.PersonalInfo.ActivityInfoParameter;
import org.nju.sesta.sams.parameter.PersonalInfo.BasicInfoParameter;
import org.nju.sesta.sams.parameter.PersonalInfo.DevFormInfoParameter;
import org.nju.sesta.sams.response.activityInfo.RoughActivityInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalInfoService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    MessageRepository messageRepo;

    @Autowired
    AuthUpReqRepository authUpReqRepo;


    public User getPersonalInfo(String id) {
        return userRepo.findByEmail(id);
    }

    public void updateBasicInfo(String id, BasicInfoParameter parameter) {
        userRepo.updateBasicInfo(parameter.getName(), parameter.getContactInformation(), parameter.getClazz(), id);
    }

    public void applyForAuthUpdating(RoleName targetRole, String id) {
        AuthUpRequest request = new AuthUpRequest();
        request.setStudentId(id);
        request.setTargetRole(targetRole);
        authUpReqRepo.save(request);//这里没有做各种检验;
    }

    public AuthUpRequest[] getAuthUpRequests() {
        return authUpReqRepo.findAll(Sort.by(Sort.Direction.DESC, "releasedDate"))
                .stream()
                .toArray(AuthUpRequest[]::new);
    }

    public DevAxFormItem[] getDev(String id) {
        return userRepo.findById(id).get().getDevAxForm().stream().toArray(DevAxFormItem[]::new);
    }

    public RoughActivityInfoResponse[] getActivityJoined(String id) {
        return userRepo.findById(id).get().getActivitiesJoined().stream().map(e -> new RoughActivityInfoResponse(e)).toArray(RoughActivityInfoResponse[]::new);
    }

    public RoughActivityInfoResponse[] getActivityReleased(String id) {
        return userRepo.findById(id).get().getActivitiesReleased().stream().map(e -> new RoughActivityInfoResponse(e)).toArray(RoughActivityInfoResponse[]::new);
    }

    public void processAuthUpRequest(Long id, Boolean decision) {
        AuthUpRequest request = authUpReqRepo.findById(id).get();
        if (decision) {//这部分的写法存疑，涉及多对多实体的存储
            User u = userRepo.findById(request.getStudentId()).get();
            Role role = roleRepo.findByRoleName(request.getTargetRole()).get(0);
            List<Role> roles = u.getRoles();
            roles.add(role);
            userRepo.save(u);
        }
        authUpReqRepo.delete(request);
        Message message = new Message();
        message.setStudentId(request.getStudentId());
        message.setContent("你的权限申请请求已处理，结果为:" + (decision ? "接受" : "拒绝"));
        messageRepo.save(message);
    }

    public void updateActivityInfo(String id, ActivityInfoParameter parameter) {
        User user = userRepo.findById(id).get();
        user.setActivitiesJoined(parameter.getActivities());
        userRepo.save(user);
    }

    public void updateDevAxFormInfo(String id, DevFormInfoParameter parameter) {
        User user = userRepo.findById(id).get();
        user.setDevAxForm(parameter.getDevAxForm());
        userRepo.save(user);
    }

}
