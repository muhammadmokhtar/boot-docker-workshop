package com.workshop.bootdockerworkshop.resource;
import org.assertj.core.api.Assertions;
import com.workshop.bootdockerworkshop.exception.CounteryCodeNotFoundException;
import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.util.NestedServletException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CountryControllerTest {
    private static final String Country_API_URI = "http://localhost:8080/{code}";
    private MockMvc mockMvc = null;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void before() throws Exception {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    public void after() throws Exception {
        mockMvc = null;
    }

    /*
     * Testing Happy Path scenario
     */
    @Test
    public void testProductFound() throws Exception {
        final MockHttpServletRequestBuilder builder = get(Country_API_URI, "EGY");
        final ResultActions result = mockMvc.perform(builder);
        result.andExpect(status().isOk());
    }

    /*
     * Testing Error scenario
     */
    @Test
    public void testCountryNotFound() throws Exception {
        final MockHttpServletRequestBuilder builder = get(Country_API_URI, "abc");
        Throwable throwable = null;
        try {
            final ResultActions result = mockMvc.perform(builder);
        } catch (NestedServletException nex) {
            throwable = nex.getCause();
        }
        Assertions.assertThat(throwable).isNotNull();
        Assertions.assertThat(CounteryCodeNotFoundException.class).isAssignableFrom(throwable.getClass());
        CounteryCodeNotFoundException exception = (CounteryCodeNotFoundException) throwable;
        Assertions.assertThat(exception.getMessage()).isEqualTo("INVALID_COUNTRY_CODE");
    }
}
