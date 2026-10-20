package com.wow.WowPotato.service;

import com.wow.WowPotato.dto.LeadRequest;
import com.wow.WowPotato.entity.Lead;
import com.wow.WowPotato.entity.LeadStatus;
import com.wow.WowPotato.repository.LeadRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
@Service
public class LeadService {

    private final LeadRepository repository;
    private final EmailService emailService;

    public LeadService(LeadRepository repository, EmailService emailService){
        this.repository = repository;
        this.emailService = emailService;
    }

    public Lead create(LeadRequest request){
        Lead lead = new Lead();
        lead.setFullName(request.fullName());
        lead.setCompany(request.company());
        lead.setPosition(request.position());
        lead.setEmail(request.email());
        lead.setPhone(request.phone());
        lead.setCountry(request.country());
        lead.setCity(request.city());
        lead.setCurrentBusiness(request.currentBusiness());
        lead.setFbExperience(request.fbExperience());
        lead.setExistingStores(request.existingStores());
        lead.setYearInBusiness(request.yearInBusiness());
        lead.setInvestmentRange(request.investmentRange());
        lead.setInterestedTerritory(request.interestedTerritory());
        lead.setPlannedStores(request.plannedStores());

        Lead saved = repository.save(lead);
        emailService.notifyNewLead(saved);
        return saved;
    }
    public Page<Lead> list(LeadStatus status, Pageable pageable){
        if (status == null) {
            return repository.findAll(pageable);
        }
        return repository.findByStatus(status, pageable);
    }
    public Lead update(Long id, LeadStatus status, String staff,
                       LocalDate followUp, String remarks){
        Lead lead = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lead not found: " + id));

        if (status != null) lead.setStatus(status);
        if (staff != null) lead.setAssignedStaff(staff);
        if (followUp != null) lead.setFollowUpDate(followUp);
        if (remarks != null) lead.setRemarks(remarks);

        return repository.save(lead);
    }
}
