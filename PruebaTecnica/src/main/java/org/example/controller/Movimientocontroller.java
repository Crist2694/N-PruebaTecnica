package org.example.controller;

import org.example.entity.Movimiento;
import org.example.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Movimientocontroller {

    @Autowired
    MovimientoService movimientoService;

    @GetMapping("/getMovimientos")
    public List<Movimiento> getMovimientos() {
        //return movimientoService.getAllMovimientos();
        return movimientoService.getAllMovimientos();
    }

    @GetMapping("/getMovimientos/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable(value = "id") int id) {
        try {
            Movimiento movimiento = movimientoService.getMovimientoById(id);
            return new ResponseEntity<Movimiento>(movimiento, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/addMovimiento")
    public Movimiento addMovimiento(@RequestBody Movimiento movimiento) {
        return movimientoService.addMovimiento(movimiento);

    }

    @PutMapping("/updateMovimiento/{id}")
    public ResponseEntity<Movimiento> updateMovimiento(@PathVariable(value = "id") int id, @RequestBody Movimiento movimiento) {
        try {
            Movimiento existMovimiento = movimientoService.getMovimientoById(id);

            existMovimiento.setTipo_movimiento(movimiento.getTipo_movimiento());
            existMovimiento.setFecha(movimiento.getFecha());
            existMovimiento.setValor(movimiento.getValor());
            existMovimiento.setCuenta_origen(movimiento.getCuenta_origen());
            existMovimiento.setCuenta_destino(movimiento.getCuenta_destino());


            Movimiento updatedMovimiento = movimientoService.updateMovimient(existMovimiento);
            return new ResponseEntity<Movimiento>(updatedMovimiento, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deleteMovimiento/{id}")
    public AddResponse deleteMovimiento(@PathVariable(value = "id") int id) {
        return movimientoService.deleteMovimiento(id);
    }
}
