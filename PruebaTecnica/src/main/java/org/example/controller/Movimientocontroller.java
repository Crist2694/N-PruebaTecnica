package org.example.controller;

import org.example.entity.Movimiento;
import org.example.entity.Persona;
import org.example.services.MovimientoService;
import org.example.util.JpaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class Movimientocontroller {

    @Autowired
    MovimientoService movimientoService;


    @GetMapping("/getMovimientos")
    public List<Movimiento> getMovimientos() {
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
    public ResponseEntity<Movimiento> addMovimiento(@RequestBody Movimiento movimiento) {
        try {
            movimiento = movimientoService.addMovimiento(movimiento);
            return new ResponseEntity<Movimiento>(movimiento, HttpStatus.CREATED);
        }catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
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
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deleteMovimiento/{id}")
    public ResponseEntity<Movimiento> deleteMovimiento(@PathVariable(value = "id") int id) {
        Movimiento movimiento = null;
        try {
            movimiento = movimientoService.getMovimientoById(id);
            movimientoService.deleteMovimiento(id);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Movimiento>(movimiento, HttpStatus.OK);

    }
    @GetMapping("/getMovimientoFechas/")
    @ResponseBody
    public List<Movimiento> getMovimientosFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam String nombre) {
      try {
          return  movimientoService.getAllMovimientosFecha(fecha, nombre);
      }catch (NoSuchElementException e){
          return null;
      }

    }
}
