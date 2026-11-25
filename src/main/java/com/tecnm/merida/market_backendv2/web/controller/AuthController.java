package com.tecnm.merida.market_backendv2.web.controller;

import com.tecnm.merida.market_backendv2.domain.dto.AuthRequest;
import com.tecnm.merida.market_backendv2.domain.dto.AuthResponse;
import com.tecnm.merida.market_backendv2.domain.dto.RegisterRequest;
import com.tecnm.merida.market_backendv2.persistence.crud.ClienteCrudRepository;
import com.tecnm.merida.market_backendv2.persistence.entity.Cliente;
import com.tecnm.merida.market_backendv2.web.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Registro, autenticación y generación de JWT")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ClienteCrudRepository clienteCrudRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          ClienteCrudRepository clienteCrudRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.clienteCrudRepository = clienteCrudRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar cliente", description = "Guarda un nuevo cliente en PostgreSQL con contraseña encriptada mediante BCrypt")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        if (clienteCrudRepository.existsById(request.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El ID de cliente ya existe");
        }

        Cliente cliente = new Cliente();
        cliente.setId(request.getId());
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setCelular(request.getCelular());
        cliente.setDireccion(request.getDireccion());
        cliente.setCorreoElectronico(request.getCorreoElectronico());
        cliente.setPassword(passwordEncoder.encode(request.getPassword()));

        clienteCrudRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente registrado correctamente");
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Valida credenciales contra la base de datos y devuelve un token JWT")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            String jwt = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}