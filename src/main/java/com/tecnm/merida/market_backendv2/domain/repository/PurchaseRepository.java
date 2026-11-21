package com.tecnm.merida.market_backendv2.domain.repository;

import com.tecnm.merida.market_backendv2.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    List<Purchase> getAll();
    Optional<List<Purchase>> getByIdClient(String clientId);
    Purchase save(Purchase purchase);
}
