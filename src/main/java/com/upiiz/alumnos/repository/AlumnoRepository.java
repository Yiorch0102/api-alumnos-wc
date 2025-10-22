package com.upiiz.alumnos.repository;

import com.upiiz.alumnos.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    // Método para buscar por matrícula y detectar duplicados
    Optional<Alumno> findByMatricula(String matricula);
}