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


    public List<Persona> getAllPersonas() {


        return personaRep.findAll();
    }

    public Persona getPersonaById(int id) {
        return personaRep.findById(id).get();
    }

    public Persona getPersonaByName(String nombre) {


        List<Persona> personas = personaRep.findAll();
        Persona persona = null;

        for (Persona per : personas) {
            if (per.getNombre().equalsIgnoreCase(nombre))
                persona = per;
        }
        return persona;
    }

    public Persona addPersona(Persona persona) {

        persona.setId(getMaxId());
        personaRep.save(persona);
        return persona;

    }

    public int getMaxId() {

        return personaRep.findAll().size() + 1;
    }

    public Persona updatePersona(Persona persona) {

        personaRep.save(persona);
        return persona;
    }

    public AddResponse deletePersona(int id) {

        personaRep.deleteById(id);
        AddResponse res = new AddResponse();
        res.setMsg("Persona eliminada ...!");
        res.setId(id);
        return res;
    }

}
