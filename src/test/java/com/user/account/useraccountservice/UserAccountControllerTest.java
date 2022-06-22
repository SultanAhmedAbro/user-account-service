package com.user.account.useraccountservice;

import java.io.IOException;

import com.user.account.useraccountservice.dto.request.UserAccountRequest;
import com.user.account.useraccountservice.dto.response.UserAccountResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserAccountControllerTest {


    @Autowired
    protected WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;

    private final String GET_ACCOUNTS_URL = "/user-accounts";
    private final String CREATE_ACCOUNT_URL = "/user-account";

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void testCrud() throws Exception {


        MvcResult mvcResult = getUserAccountsTest();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        UserAccountResponse[] userAccountResponses = mapFromJson(content, UserAccountResponse[].class);
        assertTrue(userAccountResponses.length == 0);

        UserAccountRequest userAccountRequest = new UserAccountRequest();
        userAccountRequest.setUserName("Sultan");
        userAccountRequest.setEmail("sultanahmed8883@gmail.com");
        userAccountRequest.setPassword("Pakistan@123");
        mvcResult = createUserAccount(userAccountRequest);

        assertEquals( 200, mvcResult.getResponse().getStatus());


        mvcResult = getUserAccountsTest();
        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        content = mvcResult.getResponse().getContentAsString();
        userAccountResponses = mapFromJson(content, UserAccountResponse[].class);
        assertTrue(userAccountResponses.length == 1);
        assertEquals("sultanahmed8883@gmail.com",userAccountResponses[0].getEmail());

        mvcResult = createUserAccount(userAccountRequest);

        assertEquals(409, mvcResult.getResponse().getStatus());
    }

    MvcResult getUserAccountsTest() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(GET_ACCOUNTS_URL)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    }

    MvcResult createUserAccount(UserAccountRequest userAccountRequest) throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders.post(CREATE_ACCOUNT_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(userAccountRequest))).andReturn();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
