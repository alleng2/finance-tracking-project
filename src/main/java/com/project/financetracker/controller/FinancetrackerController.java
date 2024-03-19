package com.project.financetracker.controller;

import java.time.LocalDate;
import java.util.*;
import java.sql.Date;

import com.project.financetracker.entity.TransactionInfo;
import com.project.financetracker.entity.UserInfo;
import com.project.financetracker.services.RegisterService;
import com.project.financetracker.services.TransactionService;

import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Hello, world!");
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Test is working!");
    }

    @GetMapping("/register_user")
    public ResponseEntity<String> registerUser(@RequestParam(name = "un") String un,
                                               @RequestParam(name = "email") String mail,
                                               @RequestParam(name = "fn") String fn,
                                               @RequestParam(name = "ln") String ln) {
        UserInfo parsed = UserInfo.builder().username(un).email(mail).firstName(fn).lastName(ln).build(); // build the object
        UserInfo resp = registerService.registerUser(parsed); // pass in the object

        if (resp == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Invalid input.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(resp.toString());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<String>> getUsers() {
        List<String> users = new ArrayList<>();
        List<UserInfo> queryResult = registerService.getAllUsernamesService();
        for (int i = 0; i < queryResult.size(); i++) {
            users.add(queryResult.get(i).getUsername());
        }

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/make_trans")
    public ResponseEntity<String> makeTrans(@RequestParam(name = "id") String userId,
                                            @RequestParam(name = "cost") String cost,
                                            @RequestParam(name = "pr") String priority) {
        int realId;
        float realCost;
        int realPriority;
        try {
            realId = Integer.parseInt(userId); // should throw a NumberFormatException if not a number
            realCost = Float.parseFloat(cost);
            realPriority = Integer.parseInt(priority);
        } catch (final NumberFormatException e) {
            // System.out.println("Throwing an exception...");
            throw new NumberFormatException();
        }

        java.sql.Date currentDate = Date.valueOf(LocalDate.now());
        TransactionInfo trans = TransactionInfo.builder().userId(realId).transactionDate(currentDate).cost(realCost).priority(realPriority).build();

        if (transactionService.addTransaction(trans) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Invalid input.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Successfully added a transaction of $" + cost + " to user with ID " + userId + "!");
        }
    }
}
