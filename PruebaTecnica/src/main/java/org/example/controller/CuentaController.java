package org.example.controller;

import org.example.entity.Cuenta;
import org.example.entity.Persona;
import org.example.services.CuentaService;
import org.example.util.JpaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CuentaController {

    @Autowired
    CuentaService cuentaService;


    @GetMapping("/getCuentas")
    public ResponseEntity<List<Cuenta>> getCuentas() {
        try {
            List<Cuenta>cuentas= cuentaService.getAllCuentas();
            return new ResponseEntity<List<Cuenta>>(cuentas, HttpStatus.FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCuentasId/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable(value = "id") int id) {
        try {
            Cuenta cuenta = cuentaService.getCuentaById(id);
            return new ResponseEntity<Cuenta>(cuenta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/getCuentasNumero/{numero_cuenta}")
    public ResponseEntity<Cuenta> getCuentaByNumero(@RequestParam(value = "numero_cuenta") Long numero_cuenta) {
        try {
            Cuenta cuenta = cuentaService.getCuentaByNumero(numero_cuenta);
            return new ResponseEntity<Cuenta>(cuenta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addCuenta")
    public ResponseEntity<Cuenta> addCuenta(@RequestBody Cuenta cuenta) {
        try {
            cuenta = cuentaService.addCuenta(cuenta);
            return new ResponseEntity<Cuenta>(cuenta, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/updateCuenta/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable(value = "id") int id, @RequestBody Cuenta cuenta) {
        try {
            Cuenta existeCuenta = cuentaService.getCuentaById(id);

            existeCuenta.setNumero_cuenta(cuenta.getNumero_cuenta());
            existeCuenta.setTipo_cuenta(cuenta.getTipo_cuenta());
            existeCuenta.setEstado(cuenta.getEstado());
            existeCuenta.setSaldo_inicial(cuenta.getSaldo_inicial());
            existeCuenta.setIdcliente(cuenta.getIdcliente());

            Cuenta updated_cuenta = cuentaService.updateCuenta(existeCuenta);
            return new ResponseEntity<Cuenta>(updated_cuenta, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deleteCuenta/{id}")
    public ResponseEntity<Cuenta> deleteCuenta(@PathVariable(value = "id") int id) {
        Cuenta cuenta = null;
        try {
            cuenta = cuentaService.getCuentaById(id);
            cuentaService.deleteCuenta(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cuenta>(cuenta, HttpStatus.OK);

    }
}
