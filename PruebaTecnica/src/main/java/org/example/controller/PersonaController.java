package org.example.controller;

import org.example.entity.Persona;
import org.example.services.PersonaService;
import org.example.util.JpaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PersonaController {

    @Autowired
    PersonaService personaService;
    EntityManager em = JpaUtil.getEntityManager();

    public PersonaController(PersonaService personaService) {

    }

    @GetMapping("/getPersonas")
    public ResponseEntity<List<Persona>> getPersona() {
        try {
            em.getTransaction().begin();
            List<Persona> personas = personaService.getAllPersonas();
            return new ResponseEntity<List<Persona>>(personas, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }finally {
            em.close();
        }

    }

    @GetMapping("/getPersonas/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable(value = "id") int id) {
        try {
            em.getTransaction().begin();
            Persona persona = personaService.getPersonaById(id);
            return new ResponseEntity<Persona>(persona, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }finally {
            em.close();
        }
    }

    @GetMapping("/getPersonas/nombrePersona")
    public ResponseEntity<Persona> getPersonaByName(@RequestParam(value = "name") String name) {
        try {
            em.getTransaction().begin();
            Persona persona = personaService.getPersonaByName(name);
            return new ResponseEntity<Persona>(persona, HttpStatus.FOUND);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }finally {
            em.close();
        }
    }

    @PostMapping("/addPersona")
    public ResponseEntity<Persona> addPersona(@RequestBody Persona persona) {
        try {
            em.getTransaction().begin();
            persona = personaService.addPersona(persona);
            em.getTransaction().commit();
            return new ResponseEntity<Persona>(persona, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }finally {
            em.close();
        }
    }

    @PutMapping("/updatePersona/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable(value = "id") int id, @RequestBody Persona persona) {

        try {
            em.getTransaction().begin();
            Persona existePersona = personaService.getPersonaById(id);

            existePersona.setNombre(persona.getNombre());
            existePersona.setGenero(persona.getGenero());
            existePersona.setEdad(persona.getEdad());
            existePersona.setIdentifacion(persona.getIdentifacion());
            existePersona.setDireccion(persona.getDireccion());
            existePersona.setTelefono(persona.getTelefono());

            Persona updatedPersona = personaService.updatePersona(existePersona);
            em.getTransaction().commit();
            return new ResponseEntity<Persona>(updatedPersona, HttpStatus.OK);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }finally {
            em.close();
        }
    }

    @DeleteMapping("/deletePersona/{id}")
    public ResponseEntity<Persona> deletePersona(@PathVariable(value = "id") int id) {

        Persona persona = null;
        try {
            em.getTransaction().begin();
            persona = personaService.getPersonaById(id);
            personaService.deletePersona(id);
            em.getTransaction().commit();
        } catch (NoSuchElementException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }finally {
            em.close();
        }
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);

    }
}
