package com.workshop.bootdockerworkshop.resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CountryControllerTest {
    private static final String Country_API_URI = "http://localhost:8080/{code}";
    private MockMvc mockMvc = null;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void before() throws Exception {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @After
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
        final ResultActions result = mockMvc.perform(builder);
        result.andExpect(status().isInternalServerError());
    }
}
