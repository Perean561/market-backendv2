package com.tecnm.merida.market_backendv2.persistence.crud;

import com.tecnm.merida.market_backendv2.persistence.entity.Cliente;
import org.springframework.data.repository.ListCrudRepository;

public interface ClienteCrudRepository extends ListCrudRepository<Cliente, String> {
}
