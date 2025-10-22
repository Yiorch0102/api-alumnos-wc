package com.upiiz.alumnos.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del alumno (autogenerado)",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY) // Requisito: Oculto en POST
    private Long id;

    @Schema(description = "Nombre completo del alumno", example = "Juan Carlos Pérez")
    @Column(length = 100, nullable = false)
    private String nombre;

    @Schema(description = "Número de matrícula (único)", example = "2020630001")
    @Column(length = 50, nullable = false, unique = true)
    private String matricula;

    @Schema(description = "Programa académico", example = "Ingeniería en Sistemas")
    @Column(length = 100)
    private String carrera;

    @Schema(description = "Promedio general con 2 decimales", example = "9.15")
    @Column(precision = 4, scale = 2) // Coincide con DECIMAL(4,2)
    private BigDecimal promedio;

    @Schema(description = "Indica si el alumno sigue inscrito", example = "true")
    @Column(nullable = false)
    private boolean activo;

    // --- Constructores ---
    public Alumno() {
    }

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public BigDecimal getPromedio() {
        return promedio;
    }

    public void setPromedio(BigDecimal promedio) {
        this.promedio = promedio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}