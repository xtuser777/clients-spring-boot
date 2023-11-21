package br.dev.xt.clients.dto.client;

import br.dev.xt.clients.entities.Client;

import java.util.List;

public class ClientResponseDTO {
    private Boolean success;
    private List<String> errors;
    private Client entity;

    public ClientResponseDTO(Boolean success, List<String> errors, Client entity) {
        this.success = success;
        this.errors = errors;
        this.entity = entity;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Client getEntity() {
        return entity;
    }

    public void setEntity(Client entity) {
        this.entity = entity;
    }
}
