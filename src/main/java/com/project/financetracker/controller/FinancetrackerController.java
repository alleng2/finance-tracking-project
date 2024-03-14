package com.project.financetracker.controller;

import java.util.*;

import com.project.financetracker.entity.UserInfo;
import com.project.financetracker.services.RegisterService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FinancetrackerController {
    private final RegisterService registerService;

    @GetMapping("/")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Test is working!";
    }

    @GetMapping("/register_user")
    public String registerUser(@RequestParam(name = "un") String un,
                               @RequestParam(name = "email") String mail,
                               @RequestParam(name = "fn") String fn,
                               @RequestParam(name = "ln") String ln) {
        UserInfo parsed = UserInfo.builder().username(un).email(mail).firstName(fn).lastName(ln).build(); // build the object
        UserInfo resp = registerService.registerUser(parsed); // pass in the object

        if (resp == null) {
            return "Invalid input.";
        } else {
            return resp.toString();
        }
    }

    @GetMapping("/users")
    public List<String> getUsers() {
        List<String> users = new ArrayList<>();
        List<UserInfo> queryResult = registerService.getAllUsernamesService();
        for (int i = 0; i < queryResult.size(); i++) {
            users.add(queryResult.get(i).getUsername());
        }

        return users;
    }
}
