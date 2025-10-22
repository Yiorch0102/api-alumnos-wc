package com.upiiz.alumnos.response;

import io.swagger.v3.oas.annotations.media.Schema;


public class RespuestaApi<T> {

    @Schema(description = "Estado de la operación (ej. OK, CREATED, NOT_FOUND)", example = "OK")
    private String estado;

    @Schema(description = "Mensaje descriptivo de la operación", example = "Recurso encontrado")
    private String mensaje;

    @Schema(description = "Datos resultantes de la operación (opcional)")
    private T valores;

    public RespuestaApi(String estado, String mensaje, T valores) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.valores = valores;
    }

    // Constructor para respuestas sin datos (ej. DELETE)
    public RespuestaApi(String estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.valores = null;
    }

    // --- Getters y Setters ---

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getValores() {
        return valores;
    }

    public void setValores(T valores) {
        this.valores = valores;
    }
}