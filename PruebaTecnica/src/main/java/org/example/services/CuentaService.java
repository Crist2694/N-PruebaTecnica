package org.example.services;

import org.example.controller.AddResponse;
import org.example.entity.Cuenta;
import org.example.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Service
public class CuentaService {
    /*
    static HashMap<Integer, Cuenta> cuentaIdMap;

    public CuentaService(){
        cuentaIdMap = new HashMap<Integer, Cuenta>();

        Cuenta cuenta1 = new Cuenta(1,"478758", "Ahorro", 2000.0, "True", 1);
        Cuenta cuenta2 = new Cuenta(2, "225487", "Corriente", 100.0, "True", 2);
        Cuenta cuenta3 = new Cuenta(3, "495878", "Ahorros", 0.0, "True", 3);
        Cuenta cuenta4 = new Cuenta(4, "496825", "Ahorros", 540.0, "True", 2);

        cuentaIdMap.put(1, cuenta1);
        cuentaIdMap.put(2, cuenta2);
        cuentaIdMap.put(3, cuenta3);
        cuentaIdMap.put(4, cuenta4);
    }

     */

    @Autowired
    CuentaRepository cuentaRep;

    public List<Cuenta> getAllCuentas(){
        /*List cuentas= new ArrayList(cuentaIdMap.values());
        return cuentas;
         */
        return cuentaRep.findAll();
    }

    public Cuenta getCuentaById(int id){
/*        Cuenta cuenta = cuentaIdMap.get(id);
        return cuenta;
 */
        return cuentaRep.findById(id).get();
    }

    public Cuenta getCuentaByNumero(String numero){
/*        Cuenta cuenta = null;
        for (int i:cuentaIdMap.keySet()){
            if (cuentaIdMap.get(i).getNumero_cuenta().equals(numero))
                cuenta=cuentaIdMap.get(i);
        }
        return cuenta;
 */
        List<Cuenta> cuentas = cuentaRep.findAll();
        Cuenta cuenta = null;

        for (Cuenta con:cuentas){
            if (con.getNumero_cuenta().equalsIgnoreCase(numero))
                cuenta = con;
        }
        return cuenta;
    }

    public Cuenta addCuenta(Cuenta cuenta){
/*        cuenta.setId(getMaxId());
        cuentaIdMap.put(cuenta.getId(), cuenta);
        return cuenta;
 */
        cuenta.setId(getMaxId());
        cuentaRep.save(cuenta);
        return cuenta;
   }

    public int getMaxId(){
/*        int max=0;
        for (int id:cuentaIdMap.keySet())
            if (max<= id)
                max=id;
        return max+1;
 */
        return cuentaRep.findAll().size()+1;
    }

    public Cuenta updateCuenta(Cuenta cuenta){
/*        if (cuenta.getId()>0)
            cuentaIdMap.put(cuenta.getId(), cuenta);
        return cuenta;
 */
        cuentaRep.save(cuenta);
        return cuenta;
    }

    public AddResponse deleteCuenta(int id){
/*        cuentaIdMap.remove(id);
        AddResponse res = new AddResponse();
        res.setMsg("Cuenta eliminada....!!");
        res.setId(id);
        return res;
 */
        cuentaRep.deleteById(id);
        AddResponse res = new AddResponse();
        res.setMsg("Cuenta eliminada...!");
        res.setId(id);
        return res;
    }

}
