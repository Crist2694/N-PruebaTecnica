package services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.controller.ClienteController;
import org.example.entity.Cliente;
import org.example.entity.Persona;
import org.example.services.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonMappingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ClienteController.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class ClienteTest {

    @Autowired
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    ClienteService clienteService;
    @MockBean
    ClienteController clienteController;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException, org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return (T) objectMapper.readValue(json, Cliente.class);
    }

    @Test
    void getClientesList() throws Exception {
        String url = "/getclientes";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(406, status);
        String content = mvcResult.getResponse().getContentAsString();

    }

    @Test
    public void getClientes() throws Exception {

        clienteService = Mockito.mock(ClienteService.class);
        clienteController = new ClienteController();
    }

    @Test
    public void itShouldReturnTheService() throws Exception {
        assertThat(clienteController).isNotNull();
    }

    @Test
    void crearCliente() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente = new Cliente(1, "1234", "True",
                new Persona(1, "Jose Lema", "M", 23,
                        "12345", "Otavalo sn y principal", "098254785"));
        Mockito.when(clienteService.addCliente(Mockito.any(Cliente.class))).thenReturn(cliente);
        Mockito.when(clienteService.getAllClientes()).thenReturn(clientes);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/getclientes")
                .accept(MediaType.APPLICATION_JSON).content("cliente")
                .contentType(MediaType.APPLICATION_JSON);

/*        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        //Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        Assertions.assertEquals("http://localhost:8080/addcliente", response.getHeader(HttpHeaders.LOCATION));

 */

    }
}
