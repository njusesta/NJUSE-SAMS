package org.nju.sesta.sams.inition;


import org.nju.sesta.sams.dao.ActivityRepository;
import org.nju.sesta.sams.dao.RoleRepository;
import org.nju.sesta.sams.dao.UserRepository;
import org.nju.sesta.sams.entity.Activity;
import org.nju.sesta.sams.entity.Role;
import org.nju.sesta.sams.entity.User;
import org.nju.sesta.sams.enums.ActivityKind;
import org.nju.sesta.sams.enums.RoleName;
import org.nju.sesta.sams.response.personalInfo.ContactInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 初始化runner类，在spring项目启动后自动运行，可用于初始化数据库
 *
 * @author Nosolution
 * @version 1.0
 * @since 2019/4/4
 */
@Component
public class InitializationRunner implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        initRole();
        initStudent();
        initAdmin();
        initActivity();

    }

    private void initRole() {
        if (!roleRepository.existsRoleByRoleName(RoleName.STUDENT)) {
            Role role = new Role();
            role.setRoleName(RoleName.STUDENT);
            roleRepository.saveAndFlush(role);
        }

        if (!roleRepository.existsRoleByRoleName(RoleName.MONITOR)) {
            Role role = new Role();
            role.setRoleName(RoleName.MONITOR);
            roleRepository.saveAndFlush(role);
        }

        if (!roleRepository.existsRoleByRoleName(RoleName.HEAD)) {
            Role role = new Role();
            role.setRoleName(RoleName.HEAD);
            roleRepository.saveAndFlush(role);
        }

        if (!roleRepository.existsRoleByRoleName(RoleName.ADMIN)) {
            Role role = new Role();
            role.setRoleName(RoleName.ADMIN);
            roleRepository.saveAndFlush(role);
        }
    }

    private void initStudent() {
        if (!userRepository.existsById("171250701")) {
            User user1 = new User();
            user1.setId("171250701");
            user1.setName("张三");
            user1.setEmail("171250701@smail.nju.edu.cn");
            user1.setGrade("17");
            user1.setClazz("1");
            user1.setPasswordEncrypted("$2a$10$ZijZdzfGHQUZ4OCKetwbTO3UfuKyxN9B5tk7KHbHec.9zv4aWXmAC");//123456789
            Role student = roleRepository.findByRoleName(RoleName.STUDENT).get(0);
            user1.setRoles(new ArrayList<>(Arrays.asList(student)));
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -2);
            user1.setLastLogoutDate((Calendar) c.clone());
            user1.setLastPasswordResetDate((Calendar) c.clone());
            ContactInformation ci = new ContactInformation();
            ci.setQqNumber("123123123");
            ci.setWcNumber("z123123123");
            ci.setPhoneNumber("18107791111");
            user1.setContactInformation(ci);

            userRepository.saveAndFlush(user1);
        }


        if (!userRepository.existsById("171250702")) {
            User user2 = new User();
            user2.setId("171250702");
            user2.setName("李四");
            user2.setEmail("171250702@smail.nju.edu.cn");
            user2.setGrade("17");
            user2.setClazz("1");
            user2.setPasswordEncrypted("$2a$10$NjRbRIgu3bluRLNLtr3GOegbfSizWLBIxCWjO6Cay5gkpnZ0fsTJq");//987654321
            Role student = roleRepository.findByRoleName(RoleName.STUDENT).get(0);
            user2.setRoles(new ArrayList<>(Arrays.asList(student)));
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -2);
            user2.setLastLogoutDate((Calendar) c.clone());
            user2.setLastPasswordResetDate((Calendar) c.clone());
            ContactInformation ci = new ContactInformation();
            ci.setQqNumber("456456456");
            ci.setWcNumber("z456456456");
            ci.setPhoneNumber("18107792222");
            user2.setContactInformation(ci);

            userRepository.saveAndFlush(user2);
        }

        if (!userRepository.existsById("171250703")) {
            User user3 = new User();
            user3.setId("171250703");
            user3.setName("王五");
            user3.setEmail("171250703@smail.nju.edu.cn");
            user3.setGrade("17");
            user3.setClazz("2");
            user3.setPasswordEncrypted("$2a$10$Q9FfMV0TT4pK99tlqgU0BeUktCpHR39WMEKnKdo3kYt0AsB7Wxd3O");//asdfghjkl
            Role student = roleRepository.findByRoleName(RoleName.STUDENT).get(0);
            user3.setRoles(new ArrayList<>(Arrays.asList(student)));
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -2);
            user3.setLastLogoutDate((Calendar) c.clone());
            user3.setLastPasswordResetDate((Calendar) c.clone());
            ContactInformation ci = new ContactInformation();
            ci.setQqNumber("789789789");
            ci.setWcNumber("z789789798");
            ci.setPhoneNumber("18107793333");
            user3.setContactInformation(ci);

            userRepository.saveAndFlush(user3);
        }
    }

    private void initAdmin() {
        if (!userRepository.existsById("171250704")) {
            User user = new User();
            user.setId("171250704");
            user.setName("赵六");
            user.setEmail("171250704@smail.nju.edu.cn");
            user.setGrade("17");
            user.setClazz("1");
            user.setPasswordEncrypted("$2a$10$KD7VgoriUr9AjwKimdqgWOl3vit9MbRFXXFXyWTaBUJmj37vw6Zdi");//19191919
            Role student = roleRepository.findByRoleName(RoleName.STUDENT).get(0);
            Role admin = roleRepository.findByRoleName(RoleName.ADMIN).get(0);
            user.setRoles(new ArrayList<>(Arrays.asList(student, admin)));
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -2);
            user.setLastLogoutDate((Calendar) c.clone());
            user.setLastPasswordResetDate((Calendar) c.clone());
            ContactInformation ci = new ContactInformation();
            ci.setQqNumber("110110110");
            ci.setWcNumber("z110110110");
            ci.setPhoneNumber("18107794444");
            user.setContactInformation(ci);

            userRepository.saveAndFlush(user);
        }
    }
    private void initActivity() throws Exception{
        if(!activityRepository.existsActivityByname("2018秋季篮球赛")){
            Activity a = new Activity();
            String str="2019-05-20";
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Date date =sdf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            String string="2019-6-27";
            SimpleDateFormat sdfs= new SimpleDateFormat("yyyy-MM-dd");
            Date dat1 =sdfs.parse(string);
            Calendar calendar1 = Calendar.getInstance();

            calendar.setTime(dat1);
            a.setName("2018秋季篮球赛");
            a.setContent("软院第8场蓝球大赛");
            a.setInitDate(Calendar.getInstance());
            a.setKind(ActivityKind.MATCH);
            a.setType("社团活动");
            a.setLimited(5);
            a.setRegStartDate(calendar);
            a.setRegEndDate(calendar1);
            a.setWay2Register("现场报名");
            a.setPassed(false);
            User u=userRepository.findById("171250704").get();
            a.setCreator(u);
            List<Activity> activities=u.getActivitiesReleased();
            activities.add(a);
            u.setActivitiesReleased(activities);
            userRepository.save(u);
        }
        if(!activityRepository.existsActivityByname("足球赛")){
            Activity a = new Activity();
            String str="2018-05-27";
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Date date =sdf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            String string="2019-5-27";
            SimpleDateFormat sdfs= new SimpleDateFormat("yyyy-MM-dd");
            Date dat1 =sdfs.parse(string);
            Calendar calendar1 = Calendar.getInstance();

            calendar.setTime(dat1);
            a.setName("足球赛");
            a.setPassed(false);
            a.setContent("软院第8场足球大赛");
            a.setInitDate(Calendar.getInstance());
            a.setKind(ActivityKind.MATCH);
            a.setType("社团活动");
            a.setLimited(45);
            a.setRegStartDate(calendar);
            a.setRegEndDate(calendar1);
            a.setWay2Register("网上报名");
            User u=userRepository.findById("171250702").get();
            a.setCreator(u);
            u.getActivitiesReleased().add(a);
            userRepository.save(u);
        }
        if(!activityRepository.existsActivityByname("一次讲座")){
            Activity a = new Activity();
            String str="2018-04-30";
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Date date =sdf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            String string="2019-5-24";
            SimpleDateFormat sdfs= new SimpleDateFormat("yyyy-MM-dd");
            Date dat1 =sdfs.parse(string);
            Calendar calendar1 = Calendar.getInstance();
            a.setPassed(false);
            calendar.setTime(dat1);
            a.setName("一次讲座");
            a.setContent("一场讲座，，大大大大我发");
            a.setInitDate(Calendar.getInstance());
            a.setKind(ActivityKind.OTHERS);
            a.setType("社团活动");
            a.setLimited(15);
            a.setRegStartDate(calendar);
            a.setRegEndDate(calendar1);
            a.setWay2Register("现场报名");
            User u=userRepository.findById("171250701").get();
            a.setCreator(u);
            u.getActivitiesReleased().add(a);
            userRepository.save(u);
        }
        if(!activityRepository.existsActivityByname("2018老门东越野")){
            Activity a = new Activity();
            String str="2018-05-01";
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Date date =sdf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            String string="2019-05-02";
            SimpleDateFormat sdfs= new SimpleDateFormat("yyyy-MM-dd");
            Date dat1 =sdfs.parse(string);
            Calendar calendar1 = Calendar.getInstance();
            a.setPassed(false);
            calendar.setTime(dat1);
            a.setName("2018老门东越野");
            a.setContent("老门东越野啊大刀阔斧的肌肤飓风拒绝");
            a.setInitDate(Calendar.getInstance());
            a.setKind(ActivityKind.OTHERS);
            a.setType("社团活动");
            a.setLimited(30);
            a.setRegStartDate(calendar);
            a.setRegEndDate(calendar1);
            a.setWay2Register("网上报名");
            User u=userRepository.findById("171250701").get();
            a.setCreator(u);
            u.getActivitiesReleased().add(a);
            userRepository.save(u);
        }
   }
}
