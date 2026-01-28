package com.example.bankcards.service;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.CardStatus;
import com.example.bankcards.entity.Transfer;
import com.example.bankcards.entity.User;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.repository.TransferRepository;
import com.example.bankcards.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final CardRepository cardRepository;
    private final TransferRepository transferRepository;
    private final UserRepository userRepository;

    @Transactional
    public void transfer(String username, Long fromId, Long toId, BigDecimal amount) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Card fromCard = cardRepository.findByIdAndOwner(fromId, user)
                .orElseThrow(() -> new RuntimeException("From card not found"));

        Card toCard = cardRepository.findByIdAndOwner(toId, user)
                .orElseThrow(() -> new RuntimeException("To card not found"));

        if (fromCard.getStatus() != CardStatus.ACTIVE ||
                toCard.getStatus() != CardStatus.ACTIVE) {
            throw new RuntimeException("Card is not active");
        }

        if (fromCard.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Not enough balance");
        }

        fromCard.setBalance(fromCard.getBalance().subtract(amount));
        toCard.setBalance(toCard.getBalance().add(amount));

        Transfer transfer = Transfer.builder()
                .fromCard(fromCard)
                .toCard(toCard)
                .amount(amount)
                .createdAt(LocalDateTime.now())
                .build();

        transferRepository.save(transfer);
    }
}

