package com.wow.WowPotato.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table (name = "leads")
@Getter
@Setter
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String fullName;
    private String company;
    private String position;
    private String email;
    private String phone;
    private String country;
    private String city;

    private String currentBusiness;
    private String fbExperience;
    private Integer existingStores;
    private Integer yearInBusiness;

    private String investmentRange; // "<100K", "100-250K", ...
    private String interestedTerritory;
    private String plannedStores;  // "1", "2-5", "5-10", "10+"

    @Enumerated(EnumType.STRING)
    private LeadStatus status = LeadStatus.NEW;

    private String assignedStaff;
    private LocalDate followUpDate;
    @Column(length = 2000)
    private String remarks;

    private LocalDateTime createdAt = LocalDateTime.now();
}
