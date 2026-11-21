package com.tecnm.merida.market_backendv2.domain.repository;

import com.tecnm.merida.market_backendv2.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> getAll();
    Optional<Client> getClient(String id);
    Client save(Client client);
    void delete(String id);
}
