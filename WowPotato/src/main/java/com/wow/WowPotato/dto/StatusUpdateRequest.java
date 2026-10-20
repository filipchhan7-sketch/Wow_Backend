package com.wow.WowPotato.dto;

import com.wow.WowPotato.entity.LeadStatus;

import java.time.LocalDate;

public record StatusUpdateRequest(
        LeadStatus status,
        String assignStaff,
        LocalDate followUpDate,
        String remarks
) {}
