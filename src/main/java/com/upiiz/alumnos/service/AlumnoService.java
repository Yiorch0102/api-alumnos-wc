package com.upiiz.alumnos.service;

import com.upiiz.alumnos.entity.Alumno;
import com.upiiz.alumnos.repository.AlumnoRepository;
import com.upiiz.alumnos.exception.DataConflictException;
import com.upiiz.alumnos.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> obtenerTodos() {
        return alumnoRepository.findAll();
    }

    public Alumno obtenerPorId(Long id) {
        return alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el alumno con el ID: " + id));
    }

    public Alumno crearAlumno(Alumno alumno) {
        // Validación 409: Conflicto (duplicado)
        Optional<Alumno> existente = alumnoRepository.findByMatricula(alumno.getMatricula());
        if (existente.isPresent()) {
            throw new DataConflictException("La matrícula '" + alumno.getMatricula() + "' ya está registrada.");
        }
        return alumnoRepository.save(alumno);
    }

    public Alumno actualizarAlumno(Long id, Alumno alumnoDetalles) {
        // Validación 404: No encontrado
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el alumno con el ID: " + id));

        // Validación 409: Conflicto de matrícula al actualizar
        Optional<Alumno> existenteConMatricula = alumnoRepository.findByMatricula(alumnoDetalles.getMatricula());
        if (existenteConMatricula.isPresent() && !existenteConMatricula.get().getId().equals(id)) {
            throw new DataConflictException("La matrícula '" + alumnoDetalles.getMatricula() + "' ya pertenece a otro alumno.");
        }

        // Actualizamos los campos
        alumno.setNombre(alumnoDetalles.getNombre());
        alumno.setMatricula(alumnoDetalles.getMatricula());
        alumno.setCarrera(alumnoDetalles.getCarrera());
        alumno.setPromedio(alumnoDetalles.getPromedio());
        alumno.setActivo(alumnoDetalles.isActivo());

        return alumnoRepository.save(alumno);
    }

    public void eliminarAlumno(Long id) {
        // Validación 404: No encontrado
        if (!alumnoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró el alumno con el ID: " + id);
        }
        alumnoRepository.deleteById(id);
    }
}