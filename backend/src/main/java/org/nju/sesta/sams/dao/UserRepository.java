package org.nju.sesta.sams.dao;

import org.nju.sesta.sams.entity.User;
import org.nju.sesta.sams.response.personalInfo.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Calendar;

/**
 * 继承jpa接口的面向{@link User}的数据库接口
 *
 * @author Nosolution
 * @version 1.0
 * @see User
 * @since 2019/2/4
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String id);

    /**
     * 更新用户的最后登出时间
     *
     * @param now 当前时间，即为用户登出时间
     * @param id  用户id
     * @return 影响行数
     */
    @Transactional
    @Modifying
    @Query("update User u set u.lastLogoutDate = ?1 where u.id = ?2")
    int updateLastLogoutDate(Calendar now, String id);

    /**
     * 更新用户密码并更新最后更改密码时间
     *
     * @param passwordEncrypted 新密码
     * @param now               当前时间
     * @param id                用户id
     * @return 影响行数
     */
    @Transactional
    @Modifying
    @Query("update User u set u.passwordEncrypted=?1, u.lastPasswordResetDate = ?2 where u.id = ?3")
    int resetPassword(String passwordEncrypted, Calendar now, String id);


    /**
     * 更新用户基本信息
     *
     * @param name        用户名
     * @param contactInfo 联络方式
     * @param clazz       所属班级
     * @param id          用户id
     * @return 影响行数
     */
    @Transactional
    @Modifying
    @Query("update User u set u.name=?1, u.contactInformation = ?2 , u.clazz = ?3 where u.id = ?4")
    int updateBasicInfo(String name, ContactInformation contactInfo, String clazz, String id);//需要研究自定义

}
