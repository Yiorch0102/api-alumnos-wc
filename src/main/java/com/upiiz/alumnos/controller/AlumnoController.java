package com.upiiz.alumnos.controller;

import com.upiiz.alumnos.entity.Alumno;
import com.upiiz.alumnos.response.RespuestaApi;
import com.upiiz.alumnos.service.AlumnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
@Tag(name = "Gestión de Alumnos", description = "Endpoints para el CRUD de alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @Operation(summary = "Crear un nuevo alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recurso creado correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaApi.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflicto (matrícula duplicada)", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<RespuestaApi<Alumno>> crearAlumno(@RequestBody Alumno alumno) {
        Alumno alumnoCreado = alumnoService.crearAlumno(alumno);
        RespuestaApi<Alumno> respuesta = new RespuestaApi<>("CREATED", "Recurso creado correctamente", alumnoCreado);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED); // 201
    }

    @Operation(summary = "Obtener un alumno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recurso encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaApi.class))),
            @ApiResponse(responseCode = "404", description = "No encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<Alumno>> obtenerAlumnoPorId(@PathVariable Long id) {
        Alumno alumno = alumnoService.obtenerPorId(id);
        RespuestaApi<Alumno> respuesta = new RespuestaApi<>("OK", "Recurso encontrado", alumno);
        return ResponseEntity.ok(respuesta); // 200
    }

    @Operation(summary = "Obtener lista de todos los alumnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recursos encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaApi.class)))
    })
    @GetMapping
    public ResponseEntity<RespuestaApi<List<Alumno>>> obtenerTodos() {
        List<Alumno> alumnos = alumnoService.obtenerTodos();
        RespuestaApi<List<Alumno>> respuesta = new RespuestaApi<>("OK", "Recursos encontrados", alumnos);
        return ResponseEntity.ok(respuesta); // 200
    }

    @Operation(summary = "Actualizar un alumno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recurso actualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaApi.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "No encontrado", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflicto (matrícula duplicada)", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<RespuestaApi<Alumno>> actualizarAlumno(@PathVariable Long id, @RequestBody Alumno alumnoDetalles) {
        Alumno alumnoActualizado = alumnoService.actualizarAlumno(id, alumnoDetalles);
        RespuestaApi<Alumno> respuesta = new RespuestaApi<>("OK", "Recurso actualizado", alumnoActualizado);
        return ResponseEntity.ok(respuesta); // 200
    }

    @Operation(summary = "Eliminar un alumno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eliminación exitosa", // 200 como pide tu doc
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaApi.class))),
            @ApiResponse(responseCode = "404", description = "No encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaApi<Object>> eliminarAlumno(@PathVariable Long id) {
        alumnoService.eliminarAlumno(id);
        RespuestaApi<Object> respuesta = new RespuestaApi<>("OK", "Eliminación exitosa");
        return ResponseEntity.ok(respuesta); // 200 (o 204 No Content si no devuelves cuerpo)
    }
}