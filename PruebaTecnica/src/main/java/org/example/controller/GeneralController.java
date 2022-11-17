package org.example.controller;

import org.example.entity.Persona;
import org.example.util.JpaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
public class GeneralController {
/*
    EntityManager em = JpaUtil.getEntityManager();

    @GetMapping("/Cuentas")
    public List<Persona> getPersonas() {
        List<Persona> personas = em.createQuery("select p from Persona p", Persona.class)
                .getResultList();

        return personas;
    }

 */
}
