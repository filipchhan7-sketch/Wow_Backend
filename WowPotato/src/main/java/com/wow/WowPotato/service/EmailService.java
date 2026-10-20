package com.wow.WowPotato.service;

import com.wow.WowPotato.entity.Lead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final ObjectProvider<JavaMailSender> mailSenderProvider;

    @Value("${app.notify-to:}")
    private String notifyTo;

    public EmailService(ObjectProvider<JavaMailSender> mailSenderProvider) {
        this.mailSenderProvider = mailSenderProvider;
    }

    @Async
    public void notifyNewLead(Lead lead) {
        JavaMailSender mailSender = mailSenderProvider.getIfAvailable();
        if (mailSender == null || notifyTo.isBlank()) {
            log.warn("Mail not configured, skipping email for lead {}", lead.getId());
            return;
        }
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(notifyTo);
            msg.setSubject("New franchise lead: " + lead.getFullName());
            msg.setText(
                    "Name: " + lead.getFullName() + "\n" +
                            "Company: " + lead.getCompany() + "\n" +
                            "Email: " + lead.getEmail() + "\n" +
                            "Phone: " + lead.getPhone() + "\n" +
                            "Country/City: " + lead.getCountry() + " / " + lead.getCity() + "\n" +
                            "Investment: " + lead.getInvestmentRange() + "\n" +
                            "Territory: " + lead.getInterestedTerritory() + "\n" +
                            "Planned stores: " + lead.getPlannedStores()
            );
            mailSender.send(msg);
        } catch (Exception e) {
            log.error("Failed to send lead email", e);
        }
    }
}
