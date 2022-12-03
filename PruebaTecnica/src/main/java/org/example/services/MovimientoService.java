package org.example.services;

import org.example.controller.AddResponse;
import org.example.entity.Cuenta;
import org.example.entity.Movimiento;
import org.example.repositories.CuentaRepository;
import org.example.repositories.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<Movimiento> getAllMovimientosFecha(LocalDate fecha, String nombre) {
        List<Movimiento> movimientos = movimientoRep.findAll();
        List<Movimiento> resultadoFechas= new ArrayList<>();
        Movimiento movimiento = null;


        for (Movimiento mov : movimientos) {
            if (mov.getFecha().equals(fecha) ) {
                if (mov.getFecha().isBefore(fecha) || mov.getFecha().equals(fecha) &&
                        mov.getCuenta_origen().getIdcliente().getIdPersona().getNombre().equals(nombre))
                    movimiento = mov;
                resultadoFechas.add(movimiento);
            }
        }
        return resultadoFechas;

    }

    public Movimiento getMovimientoById(int id) {
        return movimientoRep.findById(id).get();
    }

    public Movimiento addMovimiento(Movimiento movimiento) {
        movimiento.setId(getMaxId());
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
                        if (cuenta.getSaldo_inicial() >= movimiento.getValor()) {
                            System.out.println("Cuenta con el saldo suficiente");
                            Double saldoFinal = cuenta.getSaldo_inicial() - movimiento.getValor();
                            cuenta.setSaldo_inicial(saldoFinal);
                        } else {
                            System.out.println("saldo insuficiente");
                        }

                    } else if (cuenta.getId() == movimiento.getCuenta_destino().getId()) {
                        Double saldoFinal = movimiento.getValor() + cuenta.getSaldo_inicial();
                        cuenta.setSaldo_inicial(saldoFinal);
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
