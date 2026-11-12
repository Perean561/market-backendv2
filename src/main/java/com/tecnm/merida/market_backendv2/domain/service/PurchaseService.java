package com.tecnm.merida.market_backendv2.domain.service;


import com.tecnm.merida.market_backendv2.domain.Purchase;
import com.tecnm.merida.market_backendv2.domain.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private PurchaseRepository purchaseRepository;
    public List<Purchase> getAll() {
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByCliente(String clienteId) {
        return purchaseRepository.getByClient( clienteId );
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }


}
