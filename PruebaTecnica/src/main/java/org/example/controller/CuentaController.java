package org.example.controller;

import org.example.entity.Cuenta;
import org.example.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CuentaController {

    @Autowired
    CuentaService cuentaService;

    @GetMapping("/getCuentas")
    public List<Cuenta> getCuentas() {
        return cuentaService.getAllCuentas();
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
    public Cuenta addCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.addCuenta(cuenta);
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
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deleteCuenta/{id}")
    public AddResponse deleteCuenta(@PathVariable(value = "id") int id) {
        return cuentaService.deleteCuenta(id);
    }
}
