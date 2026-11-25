package com.tecnm.merida.market_backendv2.domain.service;

import com.tecnm.merida.market_backendv2.persistence.crud.ClienteCrudRepository;
import com.tecnm.merida.market_backendv2.persistence.entity.Cliente;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClienteCrudRepository clienteCrudRepository;

    public UserDetailsServiceImpl(ClienteCrudRepository clienteCrudRepository) {
        this.clienteCrudRepository = clienteCrudRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteCrudRepository.findByCorreoElectronico(username)
                .or(() -> clienteCrudRepository.findById(username))
                .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado: " + username));

        if (cliente.getPassword() == null || cliente.getPassword().isBlank()) {
            throw new UsernameNotFoundException("El cliente no tiene una contraseña configurada");
        }

        return new User(
                username,
                cliente.getPassword(),
                Collections.emptyList()
        );
    }
}