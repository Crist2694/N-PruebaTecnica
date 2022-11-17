package org.example.services;

import org.example.controller.AddResponse;
import org.example.entity.Cliente;
import org.example.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class ClienteService {

    //static HashMap<Integer, Cliente> clienteIdMap;

    @Autowired
    ClienteRepository clienteRep;

    /*
    public ClienteService(){
        clienteIdMap = new HashMap<Integer, Cliente>();

        Cliente cliente1 = new Cliente(1, "1234", "True", 1);
        Cliente cliente2 = new Cliente(2, "5678", "True", 2);
        Cliente cliente3 = new Cliente(3, "1245", "True", 3);

        clienteIdMap.put(1, cliente1);
        clienteIdMap.put(2, cliente2);
        clienteIdMap.put(3, cliente3);
    }
     */

    public List getAllClientes() {
        /*
        List clientes = new ArrayList(clienteIdMap.values());
        return clientes;
         */

        return clienteRep.findAll();
    }

    public Cliente getClienteById(int id) {
        /*Cliente cliente = clienteIdMap.get(id);
        return cliente;
         */
        return clienteRep.findById(id).get();
    }

    public Cliente addCliente(Cliente cliente) {
       /* cliente.setId(getMaxId());
        clienteIdMap.put(cliente.getId(), cliente);
        return cliente;
        */

        cliente.setId(getMaxId());
        clienteRep.save(cliente);
        return cliente;

    }

    public int getMaxId() {
        /* int max=0;
        for (int id:clienteIdMap.keySet())
            if (max<=id)
                max=id;
        return max+1;
         */
        return clienteRep.findAll().size()+1;
    }

    public Cliente updateCliente(Cliente cliente) {
        /* if (cliente.getId()>0)
            clienteIdMap.put(cliente.getId(), cliente);
        return cliente;
         */

        clienteRep.save(cliente);
        return cliente;
    }

    public AddResponse deleteCliente(int id) {
        /* clienteIdMap.remove(id);
        AddResponse res = new AddResponse();
        res.setMsg("Cliente eliminado...!");
        res.setId(id);
        return res;
         */

        clienteRep.deleteById(id);

        AddResponse res = new AddResponse();
        res.setMsg("Cliente Eliminado...!!!");
        res.setId(id);
        return res;
    }
}
