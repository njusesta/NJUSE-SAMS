package org.nju.sesta.sams.dao;

import org.nju.sesta.sams.entity.Activity;
import org.nju.sesta.sams.entity.DevAxFormItem;
import org.nju.sesta.sams.entity.User;
import org.nju.sesta.sams.response.personalInfo.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String id);

    @Modifying
    @Query("update User u set u.lastLogoutDate = ?1 where u.id = ?2" )
    int updateLastLogoutDate(Calendar now, String id);

    @Modifying
    @Query("update User u set u.passwordHash=?1, u.lastPasswordResetDate = ?2 where u.id = ?3" )
    int resetPassword(String passwordEncoded, Calendar now, String id);

    //需要研究自定义
    @Modifying
    @Query("update User u set u.name=?1, u.contactInformation = ?2 , u.clazz = ?3 where u.id = ?4" )
    int updateBasicInfo(String name, ContactInformation contactInfo, String clazz,String id);

//    @Modifying
//    @Query("update User u set u.activitiesJoined=?1 where u.id = ?2" )
//    int updateActivityInfo(List<Activity> activities, String id);
//
//    @Modifying
//    @Query("update User u set u.devAxForm=?1 where u.id = ?2" )
//    int updateDevAxFormInfo(List<DevAxFormItem> devAxForm, String id);
}
