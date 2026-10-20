package com.wow.WowPotato.controller;

import com.wow.WowPotato.dto.StatusUpdateRequest;
import com.wow.WowPotato.entity.Lead;
import com.wow.WowPotato.entity.LeadStatus;
import com.wow.WowPotato.service.LeadService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/leads")
public class AdminLeadController {

    private final LeadService leadService;

    public AdminLeadController(LeadService leadService){
        this.leadService = leadService;
    }

    @GetMapping
    public Page<Lead> list(
            @RequestParam(required = false)LeadStatus status,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable){
        return leadService.list(status, pageable);
    }

    @PatchMapping("/{id}")
    public Lead update(@PathVariable Long id, @RequestBody StatusUpdateRequest request){
        return leadService.update(id, request.status(), request.assignStaff(), request.followUpDate(), request.remarks());

    }
}
