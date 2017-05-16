package hsenid.web;

import hsenid.config.AppConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
@Test
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
public class HomeControllerTest extends AbstractTestNGSpringContextTests {

/*    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Test
    public void homeTest() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.mockMvc.perform(get("/home", "/")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"));
    }*/

}