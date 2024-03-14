package com.project.financetracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Users")
@Builder
@Getter
@NoArgsConstructor // JPA requires empty
@AllArgsConstructor(access = AccessLevel.PRIVATE) // constructor for all values provided
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Username", nullable = false)
    private String username;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    public String toString() {
        return "ID: " + id + ", Username: " + username + ", Email: " + email + ", First Name: " + firstName + ", Last Name: " + lastName;
    }
}
