package com.test.lunch.controller

import com.test.lunch.ApplicationConfig
import com.test.lunch.SecurityConfig
import com.test.lunch.model.UserModel
import com.test.lunch.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder

import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import javax.servlet.Filter

import static org.hamcrest.Matchers.contains
import static org.hamcrest.Matchers.hasSize
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 *  AdminApiUserController tests
 */
@ContextConfiguration(loader = SpringApplicationContextLoader,
        classes = [ApplicationConfig,SecurityConfig])
@WebAppConfiguration
class AdminApiUserControllerSpec extends Specification
{
    @Autowired
    WebApplicationContext wac

    @Autowired
    Filter springSecurityFilterChain

    @Autowired
    UserRepository userRepository

    MockMvc mockMvc

    def setup()
    {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .addFilters(springSecurityFilterChain)
                .build()
    }

    def "Test users list api"()
    {
        given:
            users.each{u -> userRepository.save(new UserModel(u, 'password', false))}

        when:
            def response = this.mockMvc
                    .perform(new MockHttpServletRequestBuilder(requestType, url)
                        .with(user('testadmin').password('testpass').roles('ADMIN'))
                        .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())

        then:
            response
                    .andExpect(status().is(responseStatus.value()))
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            validations.each{ v -> response.andExpect(v) }

        cleanup:
            userRepository.deleteAll()

        where:
            users                      | url                         | requestType       |  responseStatus       | validations
            []                         | 'https://localhost:8443/api/admin/users'    | HttpMethod.GET    |  HttpStatus.OK        | [jsonPath('$').isArray(),jsonPath('$', hasSize(0))]
            ['user 1']                 | 'https://localhost:8443/api/admin/users'    | HttpMethod.GET    |  HttpStatus.OK        | [jsonPath('$').isArray(),jsonPath('$', hasSize(1)),jsonPath('$[0].login').value('user 1')]
            ['user 1', 'user 2']       | 'https://localhost:8443/api/admin/users'    | HttpMethod.GET    |  HttpStatus.OK        | [jsonPath('$').isArray(),jsonPath('$', hasSize(2)),jsonPath('$[*].login').value(contains('user 1', 'user 2'))]
    }

}
