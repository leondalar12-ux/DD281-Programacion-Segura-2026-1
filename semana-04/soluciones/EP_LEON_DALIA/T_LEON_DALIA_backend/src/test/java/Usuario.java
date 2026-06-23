package com.uqai.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor
public class Usuario {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio") 
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = "Los apellidos son obligatorios") 
    private String apellidos;

    @Column(unique = true, nullable = false)
    @Email(message = "Formato de email inválido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Column(nullable = false)
    private String password; // Aquí guardaremos el hash de Bcrypt más adelante

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol; 

    @Column(nullable = false)
    @NotBlank(message = "El área es obligatoria") 
    private String area;
}
