package org.example.controller;

import org.example.entity.Persona;
import org.example.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PersonaController {

    @Autowired
    PersonaService personaService;

    public PersonaController(PersonaService personaService) {

    }

    @GetMapping("/getPersonas")
    public ResponseEntity<List<Persona>> getPersona() {
        try {
            List<Persona> personas = personaService.getAllPersonas();
            return new ResponseEntity<List<Persona>>(personas, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/getPersonas/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable(value = "id") int id) {
        try {
            Persona persona = personaService.getPersonaById(id);
            return new ResponseEntity<Persona>(persona, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getPersonas/nombrePersona")
    public ResponseEntity<Persona> getPersonaByName(@RequestParam(value = "name") String name) {
        try {
            Persona persona = personaService.getPersonaByName(name);
            return new ResponseEntity<Persona>(persona, HttpStatus.FOUND);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addPersona")
    public ResponseEntity<Persona> addPersona(@RequestBody Persona persona) {
        try {
            persona = personaService.addPersona(persona);
            return new ResponseEntity<Persona>(persona, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/updatePersona/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable(value = "id") int id, @RequestBody Persona persona) {

        try {
            Persona existePersona = personaService.getPersonaById(id);

            existePersona.setNombre(persona.getNombre());
            existePersona.setGenero(persona.getGenero());
            existePersona.setEdad(persona.getEdad());
            existePersona.setIdentifacion(persona.getIdentifacion());
            existePersona.setDireccion(persona.getDireccion());
            existePersona.setTelefono(persona.getTelefono());

            Persona updatedPersona = personaService.updatePersona(existePersona);
            return new ResponseEntity<Persona>(updatedPersona, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deletePersona/{id}")
    public ResponseEntity<Persona> deletePersona(@PathVariable(value = "id") int id) {

        Persona persona = null;
        try {
            persona = personaService.getPersonaById(id);
            personaService.deletePersona(id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);

    }
}
