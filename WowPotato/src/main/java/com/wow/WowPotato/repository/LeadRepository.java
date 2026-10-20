package com.wow.WowPotato.repository;

import com.wow.WowPotato.entity.Lead;
import com.wow.WowPotato.entity.LeadStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead,Long> {
    Page<Lead> findByStatus(LeadStatus status, Pageable pageable);
}
