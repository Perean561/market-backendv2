package com.tecnm.merida.market_backendv2.web.controller;
import com.tecnm.merida.market_backendv2.domain.Client;
import com.tecnm.merida.market_backendv2.domain.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
@RestController
@RequestMapping("/clients")
@Tag(name = "Client", description = "Manage your Clients")
public class ClientController {
 private final ClientService service;
 public ClientController(ClientService service) { this.service=service; }
 @GetMapping public List<Client> getAll() { return service.getAll(); }
 @GetMapping("/{id}") public Client get(@PathVariable("id") String id) { return service.get(id); }
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Client create(@RequestBody Client value) { return service.create(value); }
 @PutMapping("/{id}") public Client update(@PathVariable("id") String id,@RequestBody Client value) { return service.update(id,value); }
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable("id") String id) { service.delete(id); }
}