package com.tecnm.merida.market_backendv2.web.controller;

import com.tecnm.merida.market_backendv2.domain.Purchase;
import com.tecnm.merida.market_backendv2.domain.service.PurchaseService;
import com.tecnm.merida.market_backendv2.web.dto.PurchaseRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchase", description = "Manage your Purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public List<Purchase> getAll() {
        return purchaseService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Purchase create(@RequestBody PurchaseRequest request) {
        return purchaseService.save(request.toPurchase());
    }
}
