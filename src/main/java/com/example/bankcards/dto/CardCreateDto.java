package com.example.bankcards.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CardCreateDto {
    private LocalDate expiryDate;
    private BigDecimal balance;
    private Long ownerId;
}

