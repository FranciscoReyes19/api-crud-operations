package com.web.analizer.payload;

import com.web.analizer.model.Expediente;

import java.util.List;
import java.util.Optional;

public class ExpedienteResponse {

    private Optional<Expediente> expediente;
    private String message;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public ExpedienteResponse(Optional<Expediente> expediente, String message) {
        this.expediente = expediente;
    }

    public ExpedienteResponse(String message) {
        this.message = message;
    }

    public Optional<Expediente> getExpediente() {
        return expediente;
    }
    public void setExpediente(Optional<Expediente> expediente) {
        this.expediente = expediente;
    }

}
