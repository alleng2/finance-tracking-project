package com.project.financetracker.services;

import com.project.financetracker.repo.UserRepo;
import com.project.financetracker.entity.UserInfo;

import lombok.*;
import org.springframework.stereotype.Service;
import jakarta.transaction.*;

import java.util.List;

@Service
@AllArgsConstructor
public class RegisterService {
    private final UserRepo userRepo;

    @Transactional()
    public UserInfo registerUser(UserInfo user) {
        return userRepo.save(user);
    }

    @Transactional()
    public List<UserInfo> getAllUsernamesService() {
        return userRepo.getAllUsernames();
    }
}
