package com.test.assignment.user.domain.repository;

import com.test.assignment.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Repository class for User Entity class
 *
 * @author Manish Gupta
 * @version $Id: UserRepository.java 1.0
 * @since 2020-11-06
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * method returns a List of user object based on the username.
     *
     * @param username username of the User object.
     * @return User object based on the username.
     */
    @Query("SELECT u FROM User u  LEFT JOIN FETCH u.workSectors where u.username = :username")
    User getUserByUsername(@Param("username") String username);
}
