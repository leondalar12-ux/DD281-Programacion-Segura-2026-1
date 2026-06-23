package com.uqai.backend.controller;

import com.uqai.backend.entity.Lead;
import com.uqai.backend.repository.LeadRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
@RequiredArgsConstructor
public class LeadController {

    private final LeadRepository leadRepository;

    // Ruta pública para que cualquiera deje sus datos en la web
    @PostMapping
    public ResponseEntity<Lead> guardarLead(@Valid @RequestBody Lead lead) {
        return ResponseEntity.ok(leadRepository.save(lead));
    }

    // Ruta protegida (RBAC) - Solo ADMIN puede ver la lista de interesados
    @GetMapping
    public ResponseEntity<List<Lead>> listarLeads() {
        return ResponseEntity.ok(leadRepository.findAll());
    }
}