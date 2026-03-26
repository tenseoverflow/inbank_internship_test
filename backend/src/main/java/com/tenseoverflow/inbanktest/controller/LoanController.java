package com.tenseoverflow.inbanktest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenseoverflow.inbanktest.dto.LoanRequest;
import com.tenseoverflow.inbanktest.dto.LoanResponse;
import com.tenseoverflow.inbanktest.service.LoanService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<LoanResponse> getLoanAmount(@Valid @RequestBody LoanRequest request) {
        return ResponseEntity.ok(loanService.getLoanAmount(request));
    }
}
