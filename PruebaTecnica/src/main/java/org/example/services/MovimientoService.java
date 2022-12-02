package org.example.services;

import org.example.controller.AddResponse;
import org.example.entity.Cuenta;
import org.example.entity.Movimiento;
import org.example.repositories.CuentaRepository;
import org.example.repositories.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class MovimientoService {

    @Autowired
    MovimientoRepository movimientoRep;

    @Autowired
    CuentaRepository cuentaRep;

    @Autowired
    CuentaService cuentaService;

    public List<Movimiento> getAllMovimientos() {
        return movimientoRep.findAll();
    }

    public Movimiento getMovimientoById(int id) {
        return movimientoRep.findById(id).get();
    }

    public Movimiento addMovimiento(Movimiento movimiento) {
        movimiento.setId(getMaxId());
        System.out.println(movimiento);
        System.out.println("Cuenta origen " + movimiento.getCuenta_origen());
        System.out.println("Cuenta destino " + movimiento.getCuenta_destino());
        if (movimiento.getTipo_movimiento().contains("+")) {
            System.out.println("movimiento Credito");
            if (movimiento.getCuenta_origen().getId() == movimiento.getCuenta_destino().getId()) {
                System.out.println("Mismas cuentas");
            } else {
                System.out.println("Son diferentes cuentas");
                List<Cuenta> cuentas = cuentaService.getAllCuentas();
                cuentas.forEach(cuenta -> {
                    if (cuenta.getId() == movimiento.getCuenta_origen().getId()) {
                        System.out.println("Son iguales");

                        System.out.println("Saldo de cuenta " + cuenta.getSaldo_inicial() + " / " + "cantidad a transferir " + movimiento.getValor());
                        if (cuenta.getSaldo_inicial() >= movimiento.getValor()) {
                            System.out.println("Cuenta con el saldo suficiente");
                            Double saldoFinal = cuenta.getSaldo_inicial() - movimiento.getValor();
                            System.out.println(cuenta.getSaldo_inicial() - movimiento.getValor());
                            cuenta.setSaldo_inicial(saldoFinal);
                            System.out.println(cuenta.getSaldo_inicial());

                        } else {
                            System.out.println("saldo insuficiente");
                        }

                    } else if (cuenta.getId() == movimiento.getCuenta_destino().getId()) {
                        System.out.println("probando cuenta destino");
                        System.out.println("Saldo de cuenta: " + cuenta.getSaldo_inicial() + " / " + " cantidad a recibir " + movimiento.getValor());
                        System.out.println(movimiento.getValor() + cuenta.getSaldo_inicial());
                        Double saldoFinal = movimiento.getValor() + cuenta.getSaldo_inicial();
                        cuenta.setSaldo_inicial(saldoFinal);
                        System.out.println(cuenta.getSaldo_inicial());
                    }
                    movimientoRep.save(movimiento);
                });
            }

        }
        return movimiento;

    }

    public int getMaxId() {
        return movimientoRep.findAll().size() + 2;
    }

    public Movimiento updateMovimient(Movimiento movimiento) {
        movimientoRep.save(movimiento);
        return movimiento;
    }

    public AddResponse deleteMovimiento(int id) {

        movimientoRep.deleteById(id);
        AddResponse res = new AddResponse();
        res.setMsg("Movimiento Eliminado");
        res.setId(id);
        return res;
    }
}
