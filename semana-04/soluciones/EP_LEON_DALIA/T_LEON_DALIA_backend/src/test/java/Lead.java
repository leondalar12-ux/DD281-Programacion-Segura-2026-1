package com.uqai.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Entity
@Table(name = "leads")
@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor
public class Lead {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio") 
    private String nombre;

    @Email(message = "Formato de email inválido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    private String empresa;
    private String telefono;

    @Column(length = 1000)
    @NotBlank(message = "El mensaje no puede estar vacío")
    private String mensaje;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaRegistro;
@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
}
