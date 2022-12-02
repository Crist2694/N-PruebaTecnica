package org.example;

import org.example.entity.Cliente;
import org.example.entity.Cuenta;
import org.example.entity.Movimiento;
import org.example.entity.Persona;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class prueba {
    public static Object main(String[] args) {
/*
        Persona persona = new Persona(1, "Jose Lema", "M", 23, "12345", "Otavalo sn y principal", "098254785");
        Persona persona2 = new Persona(2, "Marianela Montalvo", "F", 22, "67891", "Amazonas y NNUU", "097548965");

        Cliente cliente = new Cliente(1, "1234", "True", persona);
        Cliente cliente2= new Cliente(2, "5678", "True", persona2);

        Cuenta cuentaOrigen = new Cuenta(1,478758L, "Ahorro", 2000.0, "True", cliente);
        Cuenta cuentaDestino= new Cuenta(2, 225487L, "Corriente", 100.0, "True", cliente2);

        Movimiento movimiento = new Movimiento(1, "hoy", "+", 200.0, cuentaOrigen, cuentaDestino);

        System.out.println(movimiento.toString());
        System.out.println(movimiento.getCuenta_origen().getNumero_cuenta());
        System.out.println(movimiento.getCuenta_destino().getNumero_cuenta());

        if (movimiento.getTipo_movimiento().contains("+")){
            //Los valores cuando son c´redito son posituvos, y los débitos son negativos.
            System.out.println("movimiento crédito");

            if (movimiento.getCuenta_origen().getNumero_cuenta() == movimiento.getCuenta_destino().getNumero_cuenta()){
                System.out.println("Mismo numero de cuenta");
            }else {
                System.out.println("Diferentes cuentas");
                if (cuentaOrigen.getSaldo_inicial() >= movimiento.getValor()) {
                    System.out.println("Saldo inicial de cuenta origen: " + cuentaOrigen.getSaldo_inicial());
                    System.out.println("Saldo inicial de cuenta destino: " + cuentaDestino.getSaldo_inicial());
                    System.out.println("Movimiento de la transacción:" + movimiento.getValor());
                    Double saldoTotal = cuentaDestino.getSaldo_inicial() + movimiento.getValor();
                    //cuentaOrigen.getSaldo_inicial()+cuentaDestino.getSaldo_inicial();
                    cuentaDestino.setSaldo_inicial(saldoTotal);
                    cuentaOrigen.setSaldo_inicial(cuentaOrigen.getSaldo_inicial() - movimiento.getValor());
                    System.out.println(saldoTotal + " Saldo total de la transacción");
                    System.out.println("Saldo final de cuenta Origen: " + cuentaOrigen.getSaldo_inicial());
                    System.out.println("Saldo final de cuenta Destino: " + cuentaDestino.getSaldo_inicial());
                } else {
                    System.out.println("Saldo no suficiente");
                }
            }
        }
    }
    public Movimiento addMovimiento(Movimiento movimiento){
        if (movimiento.getTipo_movimiento().contains("+")){
            System.out.println("Movimiento Crédito");
            if (movimiento.getCuenta_origen().getNumero_cuenta() == movimiento.getCuenta_destino().getNumero_cuenta()){
                System.out.println("Mismo numero de cuenta");
            }else {
                System.out.println("Diferentes cuentas...");
                if (movimiento.getCuenta_origen().getSaldo_inicial() >= movimiento.getValor()){
                    
                System.out.println("Saldo inicial de cuenta origen: " + movimiento.getCuenta_origen().getSaldo_inicial());
                System.out.println("Saldo inicial de cuenta destino: " + movimiento.getCuenta_destino().getSaldo_inicial());
                Double saldoTotal = movimiento.getCuenta_destino().getSaldo_inicial() + movimiento.getValor();
                movimiento.getCuenta_destino().setSaldo_inicial(saldoTotal);
                movimiento.getCuenta_origen().setSaldo_inicial(movimiento.getCuenta_origen().getSaldo_inicial() - movimiento.getValor());
                System.out.println(saldoTotal + " Saldo total de la transacción");
                System.out.println("Saldo final de cuenta Origen: " + movimiento.getCuenta_origen().getSaldo_inicial());
                System.out.println("Saldo final de cuente Destino: " + movimiento.getCuenta_destino().getSaldo_inicial());

                }else {
                    System.out.println("Saldo no suficiente");
                }
            }
        }
        return movimiento;
    }

 */
        return null;}

}
