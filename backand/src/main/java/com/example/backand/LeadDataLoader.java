package com.example.backand;

import com.example.backand.DTO.ContactMethod;
import com.example.backand.DTO.Lead;
import com.example.backand.DTO.LeadStatus;
import com.example.backand.Repository.LeadRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LeadDataLoader implements CommandLineRunner {

    private final LeadRepository leadRepository;

    public LeadDataLoader(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (leadRepository.count() == 0) { // лише якщо таблиця порожня
            for (int i = 1; i <= 50; i++) {
                Lead lead = Lead.builder()
                        .name("Тест Лід " + i)
                        .phone("+3805011122" + String.format("%02d", i))
                        .typeOfWork(i % 2 == 0 ? "Курсова" : "Дипломна")
                        .comment("Автоматично створений лід #" + i)
                        .status(LeadStatus.NEW)
                       
                        .build();
                leadRepository.save(lead);
            }
        }
    }
}