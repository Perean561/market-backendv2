package com.tecnm.merida.market_backendv2.persistence.crud;

import com.tecnm.merida.market_backendv2.persistence.entity.Categoria;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoriaCrudRepository extends ListCrudRepository<Categoria, Integer> {
}
