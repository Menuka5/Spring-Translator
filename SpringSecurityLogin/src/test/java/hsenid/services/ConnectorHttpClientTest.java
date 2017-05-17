package hsenid.services;

import hsenid.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Test
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
public class ConnectorHttpClientTest {
/*
    @Autowired
    ConnectorHttpClient connectorHttpClient;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setWac(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetAllLanguagesList() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/sendAllLanguages"))
                .andExpect(status().isOk());

    }*/
}

