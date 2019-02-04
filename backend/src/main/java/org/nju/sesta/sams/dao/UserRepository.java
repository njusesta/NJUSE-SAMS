package org.nju.sesta.sams.dao;

import org.nju.sesta.sams.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Calendar;

public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);

    @Modifying
    @Query("update User u set u.lastLogOutDate = ?1 where u.email = ?2" )
    void updateLastLogoutDate(Calendar now, String email);

    @Modifying
    @Query("update User u set u.password=?1, u.lastPasswordResetDate = ?2 where u.email = ?3" )
    void resetPassword(String password, Calendar now, String email);
}
