package org.example.controller;

import org.example.entity.Cliente;
import org.example.entity.Persona;
import org.example.services.ClienteService;
import org.example.util.JpaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    EntityManager em = JpaUtil.getEntityManager();
    @GetMapping("/getclientes")
    public List<Cliente> getClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/getclientes/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable(value = "id") int id) {
        try {
            em.getTransaction().begin();
            Cliente cliente = clienteService.getClienteById(id);
            return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }finally {
            em.close();
        }
    }

    @PostMapping("/addcliente")
    public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente) {
        try {
            em.getTransaction().begin();
            cliente = clienteService.addCliente(cliente);
            em.getTransaction().commit();
            return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }finally {
            em.close();
        }
    }

    @PutMapping("/updatecliente/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable(value = "id") int id, @RequestBody Cliente cliente) {
        try {
            em.getTransaction().begin();
            Cliente existeCliente = clienteService.getClienteById(id);

            existeCliente.setPasswd(cliente.getPasswd());
            existeCliente.setEstado(cliente.getEstado());
            existeCliente.setIdPersona(cliente.getIdPersona());

            Cliente update_cliente = clienteService.updateCliente(existeCliente);
            em.getTransaction().commit();
            return new ResponseEntity<Cliente>(update_cliente, HttpStatus.OK);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }finally {
            em.close();
        }
    }

    @DeleteMapping("/deletecliente/{id}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable(value = "id") int id) {
        Cliente cliente = null;
        try {
            cliente = clienteService.getClienteById(id);
            clienteService.deleteCliente(id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);

    }

}
