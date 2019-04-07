package org.nju.sesta.sams.runner;


import org.nju.sesta.sams.dao.RoleRepository;
import org.nju.sesta.sams.dao.UserRepository;
import org.nju.sesta.sams.entity.Role;
import org.nju.sesta.sams.entity.User;
import org.nju.sesta.sams.enums.RoleName;
import org.nju.sesta.sams.response.personalInfo.ContactInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

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

    @Override
    public void run(ApplicationArguments args) throws Exception {

        initRole();
        initStudent();
        initAdmin();


    }

    private void initRole() {
        if (!roleRepository.existsRoleByRoleName(RoleName.STUDENT)) {
            Role role = new Role();
            role.setRoleName(RoleName.STUDENT);
            roleRepository.save(role);
        }

        if (!roleRepository.existsRoleByRoleName(RoleName.MONITOR)) {
            Role role = new Role();
            role.setRoleName(RoleName.MONITOR);
            roleRepository.save(role);
        }

        if (!roleRepository.existsRoleByRoleName(RoleName.HEAD)) {
            Role role = new Role();
            role.setRoleName(RoleName.HEAD);
            roleRepository.save(role);
        }

        if (!roleRepository.existsRoleByRoleName(RoleName.ADMIN)) {
            Role role = new Role();
            role.setRoleName(RoleName.ADMIN);
            roleRepository.save(role);
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

            userRepository.save(user1);
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

            userRepository.save(user2);
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

            userRepository.save(user3);
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

            userRepository.save(user);
        }
    }
}
