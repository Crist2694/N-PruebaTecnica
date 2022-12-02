package org.example.controller;

import org.example.entity.Movimiento;
import org.example.entity.Persona;
import org.example.services.MovimientoService;
import org.example.util.JpaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class Movimientocontroller {

    @Autowired
    MovimientoService movimientoService;

    EntityManager em = JpaUtil.getEntityManager();

    @GetMapping("/getMovimientos")
    public List<Movimiento> getMovimientos() {
        return movimientoService.getAllMovimientos();
    }

    @GetMapping("/getMovimientos/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable(value = "id") int id) {
        try {
            em.getTransaction().begin();
            Movimiento movimiento = movimientoService.getMovimientoById(id);
            return new ResponseEntity<Movimiento>(movimiento, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }finally {
            em.close();
        }

    }

    @PostMapping("/addMovimiento")
    public ResponseEntity<Movimiento> addMovimiento(@RequestBody Movimiento movimiento) {
        try {
            em.getTransaction().begin();
            movimiento = movimientoService.addMovimiento(movimiento);
            em.getTransaction().commit();
            return new ResponseEntity<Movimiento>(movimiento, HttpStatus.CREATED);
        }catch (NoSuchElementException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }finally {
            em.close();
        }
    }

    @PutMapping("/updateMovimiento/{id}")
    public ResponseEntity<Movimiento> updateMovimiento(@PathVariable(value = "id") int id, @RequestBody Movimiento movimiento) {
        try {
            em.getTransaction().begin();
            Movimiento existMovimiento = movimientoService.getMovimientoById(id);

            existMovimiento.setTipo_movimiento(movimiento.getTipo_movimiento());
            existMovimiento.setFecha(movimiento.getFecha());
            existMovimiento.setValor(movimiento.getValor());
            existMovimiento.setCuenta_origen(movimiento.getCuenta_origen());
            existMovimiento.setCuenta_destino(movimiento.getCuenta_destino());


            Movimiento updatedMovimiento = movimientoService.updateMovimient(existMovimiento);
            em.getTransaction().commit();
            return new ResponseEntity<Movimiento>(updatedMovimiento, HttpStatus.OK);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }finally {
            em.close();
        }
    }

    @DeleteMapping("/deleteMovimiento/{id}")
    public ResponseEntity<Movimiento> deleteMovimiento(@PathVariable(value = "id") int id) {
        Movimiento movimiento = null;
        try {
            em.getTransaction().begin();
            movimiento = movimientoService.getMovimientoById(id);
            movimientoService.deleteMovimiento(id);
            em.getTransaction().commit();
        }catch (NoSuchElementException e){
            em.getTransaction().rollback();
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }finally {
            em.close();
        }
        return new ResponseEntity<Movimiento>(movimiento, HttpStatus.OK);

    }
}
