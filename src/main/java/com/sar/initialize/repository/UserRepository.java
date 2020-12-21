package com.sar.initialize.repository;

import com.sar.initialize.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameOrEmailOrCellPhoneAndEnabledIsTrueAndLockedIsFalseAndExpiredAfter(String userName, String email, String cellPhone, Instant expiredDate);

}
