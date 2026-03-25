package com.tenseoverflow.inbanktest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenseoverflow.inbanktest.dto.LoanRequest;
import com.tenseoverflow.inbanktest.dto.LoanResponse;
import com.tenseoverflow.inbanktest.service.LoanService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<LoanResponse> getLoanAmount(@RequestParam LoanRequest request) {
        return ResponseEntity.ok(loanService.getLoanAmount(request));
    }
}
