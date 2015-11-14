package com.test.lunch.controller

import com.test.lunch.ApplicationConfig
import org.apache.tomcat.util.security.MD5Encoder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import javax.servlet.Filter

import static org.hamcrest.Matchers.hasSize
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *  AdminApiDishController tests
 */
@ContextConfiguration(loader = SpringApplicationContextLoader,
                      classes = [ApplicationConfig])
@WebAppConfiguration
class AdminApiDishControllerSpec extends Specification
{
    @Autowired
    WebApplicationContext wac;

    @Autowired
    Filter springSecurityFilterChain

    MockMvc mockMvc;

    def setup()
    {
        mockMvc = MockMvcBuilders
                    .webAppContextSetup(this.wac)
                    .addFilters(springSecurityFilterChain)
                    .build();
    }


//    def "Non-admin users are not allowed to use dish management api"()
//    {
//        when: "User with given url"
//                def response = this.mockMvc.perform(new MockHttpServletRequestBuilder(methodType, url)
//                        .with(user(username).password(MD5Encoder.encode(userpass.bytes)).roles(userrole))
//                        .accept(MediaType.APPLICATION_JSON))
//        then: "He gets access error"
//            response
//                .andExpect(status().isNotAcceptable())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath('$', hasSize(1)))
//        where:
//                username | userpass | userrole | url | methodType
//                'test' | 'password' | 'USER' | "/api/v1/admin/dishes" | HttpMethod.GET
//    }

    def "Empty array should be returned when there are no dishes"()
    {
        when: "Admin list dishes on empty DB"
                def response = this.mockMvc.perform(get("/api/v1/admin/dishes")
                                                    .with(user('testadmin')).accept(MediaType.APPLICATION_JSON))
        then: "He gets empty array"
               response
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$', hasSize(0)))
    }

}
