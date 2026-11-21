package com.tecnm.merida.market_backendv2.domain.repository;

import com.tecnm.merida.market_backendv2.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> getAll();
    Optional<Category> getCategory(int id);
    Category save(Category category);
    void delete(int id);
}
