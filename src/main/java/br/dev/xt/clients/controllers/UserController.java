package br.dev.xt.clients.controllers;

import br.dev.xt.clients.dto.client.ClientResponseDTO;
import br.dev.xt.clients.dto.user.UserCreateDTO;
import br.dev.xt.clients.dto.user.UserResponseDTO;
import br.dev.xt.clients.dto.user.UserUpdateDTO;
import br.dev.xt.clients.entities.User;
import br.dev.xt.clients.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public List<User> index() {
        return this.userService.read();
    }

    @GetMapping(value = "/{id}")
    public Optional<User> show(@PathVariable Integer id) {
        return this.userService.read(id);
    }

    @PostMapping(value = "/")
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserCreateDTO user) {
        try {
            UserResponseDTO dto = this.userService.create(user);
            return ResponseEntity.status(dto.getSuccess() ? 201 : 400).body(dto);
        } catch (Exception e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponseDTO(false, errors, null));
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody UserUpdateDTO user) {
        try {
            UserResponseDTO dto = this.userService.update(id, user);
            return ResponseEntity.status(dto.getSuccess() ? 201 : 400).body(dto);
        } catch (Exception e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponseDTO(false, errors, null));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> delete(@PathVariable Integer id) {
        try {
            UserResponseDTO dto = this.userService.delete(id);
            return ResponseEntity.status(dto.getSuccess() ? 201 : 400).body(dto);
        } catch (Exception e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponseDTO(false, errors, null));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public UserResponseDTO handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return new UserResponseDTO(false, errors, null);
    }
}
