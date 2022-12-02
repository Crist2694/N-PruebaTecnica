package org.example.services;

import org.example.controller.AddResponse;
import org.example.entity.Cuenta;
import org.example.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class CuentaService {

    @Autowired
    CuentaRepository cuentaRep;

    public List<Cuenta> getAllCuentas() {
        return cuentaRep.findAll();
    }

    public Cuenta getCuentaById(int id) {
        return cuentaRep.findById(id).get();
    }

    public Cuenta getCuentaByNumero(Long numero) {
        List<Cuenta> cuentas = cuentaRep.findAll();
        Cuenta cuenta = null;

        for (Cuenta con : cuentas) {
            if (con.getNumero_cuenta().equals(numero))
                cuenta = con;
        }
        return cuenta;
    }

    public Cuenta addCuenta(Cuenta cuenta) {
        cuenta.setId(getMaxId());
        cuentaRep.save(cuenta);
        return cuenta;
    }

    public int getMaxId() {
        return cuentaRep.findAll().size() + 1;
    }

    public Cuenta updateCuenta(Cuenta cuenta) {
        cuentaRep.save(cuenta);
        return cuenta;
    }

    public AddResponse deleteCuenta(int id) {
        cuentaRep.deleteById(id);
        AddResponse res = new AddResponse();
        res.setMsg("Cuenta eliminada...!");
        res.setId(id);
        return res;
    }


}
