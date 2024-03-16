package com.project.financetracker.repo;

import java.util.*;

import com.project.financetracker.entity.TransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionInfo, Integer> {
}
