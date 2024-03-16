package com.project.financetracker.entity;

import java.sql.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Transactions")
@Builder
@Getter
@Setter
@NoArgsConstructor // JPA requires empty
@AllArgsConstructor(access = AccessLevel.PRIVATE) // constructor for all values provided
public class TransactionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "UserID", nullable = false)
    private int userId;

    @Column(name = "TransactionDate", nullable = false)
    private java.sql.Date transactionDate;

    @Column(name = "Cost", nullable = false)
    private float cost;

    @Column(name = "Priority")
    private int priority;
}
