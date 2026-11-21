package com.tecnm.merida.market_backendv2.persistence.mapper;
import com.tecnm.merida.market_backendv2.domain.Client;
import com.tecnm.merida.market_backendv2.persistence.entity.Cliente;
import org.mapstruct.*;
@Mapper(componentModel="spring")
public interface ClientMapper {
 @Mapping(source="id",target="clientId")
 @Mapping(source="nombre",target="name")
 @Mapping(source="apellido",target="lastName")
 @Mapping(source="celular",target="phone")
 @Mapping(source="direccion",target="address")
 @Mapping(source="correoElectronico",target="email")
 Client toClient(Cliente entity);
 @InheritInverseConfiguration
 @Mapping(target="compras",ignore=true)
 Cliente toCliente(Client client);
}