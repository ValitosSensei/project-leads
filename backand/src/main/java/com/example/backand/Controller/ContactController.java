package com.example.backand.Controller;

import com.example.backand.DTO.ContactForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {

    //Класс по обробці заявкі
    @PostMapping("/contact")
    public ResponseEntity<String> receiveContact(@RequestBody ContactForm contactForm) {
        //Логіка обробкі-збереження
        System.out.println("Отримано контакт"+ contactForm.getEmail());
        return ResponseEntity.ok("Форма отримана");
    }
}
