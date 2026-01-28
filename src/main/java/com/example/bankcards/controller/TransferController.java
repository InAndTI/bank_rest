package com.example.bankcards.controller;

import com.example.bankcards.entity.User;
import com.example.bankcards.service.TransferService;
import com.example.bankcards.dto.TransferRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public void transfer(@RequestBody TransferRequestDto dto,
                         @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();

        transferService.transfer(username, dto.getFromCardId(), dto.getToCardId(), dto.getAmount());
    }
}
