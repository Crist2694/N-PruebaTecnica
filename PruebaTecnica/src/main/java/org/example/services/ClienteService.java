package org.example.services;

import org.example.controller.AddResponse;
import org.example.entity.Cliente;
import org.example.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRep;

    public List getAllClientes() {
        return clienteRep.findAll();
    }

    public Cliente getClienteById(int id) {
        return clienteRep.findById(id).get();
    }

    public Cliente addCliente(Cliente cliente) {

        cliente.setId(getMaxId());
        clienteRep.save(cliente);
        return cliente;

    }

    public int getMaxId() {
        return clienteRep.findAll().size() + 1;
    }

    public Cliente updateCliente(Cliente cliente) {

        clienteRep.save(cliente);
        return cliente;
    }

    public AddResponse deleteCliente(int id) {

        clienteRep.deleteById(id);

        AddResponse res = new AddResponse();
        res.setMsg("Cliente Eliminado...!!!");
        res.setId(id);
        return res;
    }
}
