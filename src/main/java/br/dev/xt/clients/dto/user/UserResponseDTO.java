package br.dev.xt.clients.dto.user;

import br.dev.xt.clients.entities.User;

import java.util.List;

public class UserResponseDTO {

    private Boolean success;
    private List<String> errors;
    private User entity;

    public UserResponseDTO(Boolean success, List<String> errors, User entity) {
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

    public User getEntity() {
        return entity;
    }

    public void setEntity(User entity) {
        this.entity = entity;
    }
}
