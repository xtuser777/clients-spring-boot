package br.dev.xt.clients.controllers;

import br.dev.xt.clients.dto.client.*;
import br.dev.xt.clients.entities.Client;
import br.dev.xt.clients.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> index() {
        return this.clientService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Client> show(@PathVariable Integer id) {
        return this.clientService.findById(id);
    }

    @PostMapping(value = "/individual")
    public ResponseEntity<ClientResponseDTO> create(@Valid @RequestBody ClientCreateIndividualDTO client) {
        try {
            ClientResponseDTO dto = this.clientService.save(client);
            if (dto.getSuccess())
                return ResponseEntity.status(HttpStatus.CREATED).body(dto);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
        } catch (Exception e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ClientResponseDTO(false, errors, null));
        }
    }

    @PostMapping(value = "/enterprise")
    public ResponseEntity<ClientResponseDTO> create(@Valid @RequestBody ClientCreateEnterpriseDTO client) {
        try {
            ClientResponseDTO dto = this.clientService.save(client);
            if (dto.getSuccess())
                return ResponseEntity.status(HttpStatus.CREATED).body(dto);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
        } catch (Exception e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ClientResponseDTO(false, errors, null));
        }
    }

    @PutMapping(value = "/individual/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody ClientUpdateIndividualDTO client) {
        ClientResponseDTO dto = this.clientService.update(id, client);
        if (dto.getSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @PutMapping(value = "/enterprise/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody ClientUpdateEnterpriseDTO client) {
        ClientResponseDTO dto = this.clientService.update(id, client);
        if (dto.getSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDTO> delete(@PathVariable Integer id) {
        ClientResponseDTO dto = this.clientService.delete(id);
        if (dto.getSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ClientResponseDTO handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return new ClientResponseDTO(false, errors, null);
    }
}
