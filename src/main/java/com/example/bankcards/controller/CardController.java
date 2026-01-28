package com.example.bankcards.controller;

import com.example.bankcards.entity.Card;
import com.example.bankcards.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public Page<Card> getMyCards(
            @AuthenticationPrincipal UserDetails userDetails,
            Pageable pageable
    ) {
        String username = userDetails.getUsername();

        return cardService.getUserCards(username, pageable);
    }

    @PostMapping("/{id}/block")
    @PreAuthorize("hasRole('USER')")
    public void blockCard(@PathVariable Long id,
                          @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();

        cardService.blockCard(id, username);
    }

    @GetMapping("/{id}/balance")
    @PreAuthorize("hasRole('USER')")
    public BigDecimal getBalance(@PathVariable Long id,
                                 @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();

        return cardService.getBalance(id, username);
    }
}
