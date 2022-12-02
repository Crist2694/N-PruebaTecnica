package services;

import org.example.controller.PersonaController;
import org.example.entity.Persona;
import org.example.services.PersonaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersonaControllerTest.class)
public class PersonaControllerTest {


    @MockBean
    PersonaService personaService;
    @MockBean
    PersonaController personaController;
    @MockBean
    private PersonaControllerTest personaControllerTest;

    @Test
    public void getPersona() throws Exception {

        personaService = Mockito.mock(PersonaService.class);
        personaController = new PersonaController(personaService);
    }

    @Test
    public void itShouldReturnTheService() throws Exception {
        assertThat(personaController).isNotNull();
    }

    @org.junit.jupiter.api.Test
    void crearPersona() throws Exception {
        List<Persona> personas = new ArrayList<>();
        Persona persona = new Persona(1, "Jose Lema", "M", 23,
                        "12345", "Otavalo sn y principal", "098254785");
        Mockito.when(personaService.addPersona(Mockito.any(Persona.class))).thenReturn(persona);
        Mockito.when(personaService.getAllPersonas()).thenReturn(personas);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/getPersonas")
                .accept(MediaType.APPLICATION_JSON).content("persona")
                .contentType(MediaType.APPLICATION_JSON);
    }

    }
