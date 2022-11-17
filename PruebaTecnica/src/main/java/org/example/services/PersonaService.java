package org.example.services;

import org.example.controller.AddResponse;
import org.example.entity.Persona;
import org.example.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRep;

    //static HashMap<Integer, Persona> personaIdMap;

    /*
    public PersonaService() {
        personaIdMap = new HashMap<Integer, Persona>();

        Persona persona1 = new Persona(1, "Jose Lema", "M", 23, "12345", "Otavalo sn y principal", "098254785");
        Persona persona2 = new Persona(2, "Marianela Montalvo", "F", 22, "67891", "Amazonas y NNUU", "097548965");
        Persona persona3 = new Persona(3, "Juan Osorio ", "M", 24, "23456", "13 junio y Equinoccial", "098874587");

        personaIdMap.put(1, persona1);
        personaIdMap.put(2, persona2);
        personaIdMap.put(3, persona3);
    }

     */

    public List<Persona> getAllPersonas() {
        /*
        List personas = new ArrayList(personaIdMap.values());
        return personas;

         */

        return personaRep.findAll();
    }

    public Persona getPersonaById(int id) {
        /*
        Persona persona = personaIdMap.get(id);
        return persona;
         */
        return personaRep.findById(id).get();
    }

    public Persona getPersonaByName(String nombre) {
        /*
        Persona persona = null;
        for (int i : personaIdMap.keySet()) {
            if (personaIdMap.get(i).getNombre().equals(nombre))
                persona = personaIdMap.get(i);
        }

        return persona;

         */

        List<Persona> personas = personaRep.findAll();
        Persona persona = null;

        for (Persona per : personas) {
            if (per.getNombre().equalsIgnoreCase(nombre))
                persona = per;
        }
        return persona;
    }

    public Persona addPersona(Persona persona) {
        /*
        persona.setId(getMaxId());
        personaIdMap.put(persona.getId(), persona);
        return persona;

         */

        persona.setId(getMaxId());
        personaRep.save(persona);
        return persona;

    }

    public int getMaxId() {
        /*
        int max = 0;
        for (int id : personaIdMap.keySet())
            if (max <= id)
                max = id;
        return max + 1;

         */
        return personaRep.findAll().size() + 1;
    }

    public Persona updatePersona(Persona persona) {
        /*
        if (persona.getId() > 0)
            personaIdMap.put(persona.getId(), persona);
        return persona;

         */

        personaRep.save(persona);
        return persona;
    }

    public AddResponse deletePersona(int id) {
        /*
        personaIdMap.remove(id);
        AddResponse res = new AddResponse();
        res.setMsg("Persona eliminada...");
        res.setId(id);
        return res;

         */

        personaRep.deleteById(id);
        AddResponse res = new AddResponse();
        res.setMsg("Persona eliminada ...!");
        res.setId(id);
        return res;
    }

}
