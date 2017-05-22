package hsenid.services;

import hsenid.config.AppConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;


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

