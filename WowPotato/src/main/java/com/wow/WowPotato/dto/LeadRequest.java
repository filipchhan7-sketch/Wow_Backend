package com.wow.WowPotato.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LeadRequest(
        @NotBlank(message = "Full name is required") String fullName,
        String company,
        String position,
        @NotBlank(message = "Email is required") @Email(message = "Invalid email") String email,
        @NotBlank(message = "Phone is required") String phone,
        @NotBlank(message = "Country is required") String country,
        String city,
        String currentBusiness,
        String fbExperience,
        Integer existingStores,
        Integer yearInBusiness,
        @NotBlank(message = "Investment range is required") String investmentRange,
        @NotBlank(message = "Territory is required") String interestedTerritory,
        @NotBlank(message = "Planned stores is required") String plannedStores
        ) {}
