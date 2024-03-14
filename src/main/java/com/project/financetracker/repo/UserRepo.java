package com.project.financetracker.repo;

import java.util.*;

import com.project.financetracker.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserInfo,Integer> {

    @Query("select u from UserInfo u")
    List<UserInfo> getAllUsernames();

}
