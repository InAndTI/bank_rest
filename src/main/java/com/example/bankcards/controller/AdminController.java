package com.example.bankcards.controller;

import com.example.bankcards.service.CardService;
import com.example.bankcards.dto.CardCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final CardService cardService;

    @PostMapping("/cards")
    public void createCard(@RequestBody CardCreateDto dto) {
        cardService.createCard(dto);
    }

    @PostMapping("/cards/{id}/activate")
    public void activate(@PathVariable Long id) {
        cardService.activateCard(id);
    }

    @DeleteMapping("/cards/{id}")
    public void delete(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
}
