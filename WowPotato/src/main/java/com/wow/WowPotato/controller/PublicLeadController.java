package com.wow.WowPotato.controller;


import com.wow.WowPotato.dto.LeadRequest;
import com.wow.WowPotato.entity.Lead;
import com.wow.WowPotato.service.LeadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/public/leads")
public class PublicLeadController {

    private final LeadService leadService;

    public PublicLeadController(LeadService leadService){
        this.leadService = leadService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> submit(@Valid @RequestBody LeadRequest request){
        Lead lead = leadService.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("id", lead.getId(), "message", "Inquiry received"));
    }
}
