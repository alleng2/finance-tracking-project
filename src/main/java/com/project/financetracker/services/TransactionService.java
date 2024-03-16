package com.project.financetracker.services;

import com.project.financetracker.repo.TransactionRepo;
import com.project.financetracker.entity.TransactionInfo;

import lombok.*;
import org.springframework.stereotype.Service;
import jakarta.transaction.*;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepo transRepo;

    @Transactional()
    public TransactionInfo addTransaction(TransactionInfo tInfo) {
        return transRepo.save(tInfo);
    }

    @Transactional()
    public Optional<TransactionInfo> editTransactionCost(int id, float cost) {
        Optional<TransactionInfo> tInfo = transRepo.findById(id);
        if (tInfo.isPresent()) {
            TransactionInfo deoption = tInfo.get();
            deoption.setCost(cost);
            TransactionInfo result = transRepo.save(deoption);
            return Optional.of(result);
        }
        return Optional.empty();
    }
}
