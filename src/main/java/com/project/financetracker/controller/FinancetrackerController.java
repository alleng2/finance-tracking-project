package com.project.financetracker.controller;

import java.time.LocalDate;
import java.util.*;
import java.sql.Date;

import com.project.financetracker.entity.TransactionInfo;
import com.project.financetracker.entity.UserInfo;
import com.project.financetracker.services.RegisterService;
import com.project.financetracker.services.TransactionService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
public class FinancetrackerController {
    private final RegisterService registerService;
    private final TransactionService transactionService;

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

    @GetMapping("/make_trans")
    public String makeTrans(@RequestParam(name = "id") String userId,
                            @RequestParam(name = "cost") String cost,
                            @RequestParam(name = "pr") String priority) {
        int realId;
        float realCost;
        int realPriority;
        try {
            realId = Integer.parseInt(userId);
            realCost = Float.parseFloat(cost);
            realPriority = Integer.parseInt(priority);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong with the parsing.");
        }

        java.sql.Date currentDate = Date.valueOf(LocalDate.now());
        TransactionInfo trans = TransactionInfo.builder().userId(realId).transactionDate(currentDate).cost(realCost).priority(realPriority).build();

        if (transactionService.addTransaction(trans) == null) {
            return "Query failed.";
        } else {
            return "Successfully added a transaction of $" + cost + " to user with ID " + userId + "!";
        }
    }
}
