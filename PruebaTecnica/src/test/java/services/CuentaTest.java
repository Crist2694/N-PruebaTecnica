package services;

import org.example.controller.CuentaController;
import org.example.controller.PersonaController;
import org.example.entity.Cliente;
import org.example.entity.Cuenta;
import org.example.entity.Persona;
import org.example.services.CuentaService;
import org.example.services.PersonaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
@SpringBootTest(classes = CuentaTest.class)
@AutoConfigureMockMvc
public class CuentaTest {


    @MockBean
    CuentaService cuentaService;
    @MockBean
    CuentaController cuentaController;
    @MockBean
    private CuentaTest cuentaTest;

    @Test
    public void getPersona() throws Exception {

        cuentaService = Mockito.mock(CuentaService.class);
        cuentaController = new CuentaController();
    }

    @Test
    public void itShouldReturnTheService() throws Exception {
        assertThat(cuentaController).isNotNull();
    }

    @org.junit.jupiter.api.Test
    void crearCuenta() throws Exception {
        List<Cuenta> cuentas = new ArrayList<>();
        /*Cuenta cuenta = new Cuenta(1,"478758", "Ahorro", 2000.0, "True",
                new Cliente(1, "1234", "True",
                        new Persona(1, "Jose Lema", "M", 23,
                                "12345", "Otavalo sn y principal", "098254785")
        ));

         */
        //Mockito.when(cuentaService.addCuenta(Mockito.any(Cuenta.class))).thenReturn(cuenta);
        //Mockito.when(cuentaService.getAllCuentas()).thenReturn(cuentas);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/getCuentas")
                .accept(MediaType.APPLICATION_JSON).content("cuenta")
                .contentType(MediaType.APPLICATION_JSON);
    }

}
