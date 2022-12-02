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
    /*
    static HashMap<Integer, Movimiento> movimientoIdMap;

  //EntityManager em = JpaUtil.getEntityManager();
    public MovimientoService(){
        movimientoIdMap = new HashMap<Integer, Movimiento>();
        Persona persona = new Persona(1, "Jose Lema", "M", 23, "12345", "Otavalo sn y principal", "098254785");
        Persona persona2 = new Persona(2, "Marianela Montalvo", "F", 22, "67891", "Amazonas y NNUU", "097548965");

        Cliente cliente = new Cliente(1, "1234", "True", persona);
        Cliente cliente2= new Cliente(2, "5678", "True", persona2);

        Cuenta cuentaOrigen = new Cuenta(1,478758L, "Ahorro", 2000.0, "True", cliente);
        Cuenta cuentaDestino= new Cuenta(2, 225487L, "Corriente", 100.0, "True", cliente2);

        Movimiento movimiento1= new Movimiento(1, "hoy", "-", 200.0,1, 2);
        Movimiento movimiento2= new Movimiento(2, "ayer", "+", 200.0, 1, 2);
        Movimiento movimiento3= new Movimiento(3, "pasado", "-", 100.0, 1, 2);

        movimientoIdMap.put(1, movimiento1);
        movimientoIdMap.put(2, movimiento2);
        movimientoIdMap.put(3, movimiento3);

    }

     */

    public List<Movimiento> getAllMovimientos() {
       /* List movimientos= new ArrayList(movimientoIdMap.values());
        return movimientos;
        */

        return movimientoRep.findAll();

    }

    public Movimiento getMovimientoById(int id) {
        /*Movimiento movimiento =movimientoIdMap.get(id);
        //movimiento = em.find(Movimiento.class, movimiento.getId());
        return movimiento;
         */

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
        /*int max=0;
        for (int id:movimientoIdMap.keySet())
            if (max<=id)
                max=id;
        return max+1;
         */


        return movimientoRep.findAll().size() + 2;
    }

    public Movimiento updateMovimient(Movimiento movimiento) {
        /*if (movimiento.getId()>0)
            movimientoIdMap.put(movimiento.getId(), movimiento);
        //em.merge(movimiento);
        return movimiento;

         */

        movimientoRep.save(movimiento);
        return movimiento;
    }

    public AddResponse deleteMovimiento(int id) {
        /*movimientoIdMap.remove(id);
        AddResponse res = new AddResponse();
        res.setMsg("Movimiento Eliminado...!!");
        res.setId(id);
        return res;
         */

        movimientoRep.deleteById(id);
        AddResponse res = new AddResponse();
        res.setMsg("Movimiento Eliminado");
        res.setId(id);
        return res;
    }
}
