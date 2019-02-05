package org.nju.sesta.sams.dao;

import org.nju.sesta.sams.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Calendar;

public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String id);

    @Modifying
    @Query("update User u set u.lastLogoutDate = ?1 where u.id = ?2" )
    void updateLastLogoutDate(Calendar now, String email);

    @Modifying
    @Query("update User u set u.passwordHash=?1, u.lastPasswordResetDate = ?2 where u.id = ?3" )
    void resetPassword(String passwordEncoded, Calendar now, String email);
}
