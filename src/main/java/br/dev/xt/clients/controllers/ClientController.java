package br.dev.xt.clients.controllers;

import br.dev.xt.clients.dto.client.ClientResponseDTO;
import br.dev.xt.clients.entities.Client;
import br.dev.xt.clients.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping
    public ClientResponseDTO create(@RequestBody Client client) {
        return this.clientService.save(client);
    }
}
