package com.test.lunch.controller

import com.test.lunch.ApplicationConfig
import com.test.lunch.SecurityConfig
import com.test.lunch.model.DishModel
import com.test.lunch.repository.DishRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import javax.servlet.Filter

import static org.hamcrest.Matchers.contains
import static org.hamcrest.Matchers.hasSize
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 *  AdminApiDishController tests
 */
@ContextConfiguration(loader = SpringApplicationContextLoader,
                      classes = [ApplicationConfig,SecurityConfig])
@WebAppConfiguration
class AdminApiDishControllerSpec extends Specification
{
    @Autowired
    WebApplicationContext wac

    @Autowired
    Filter springSecurityFilterChain

    @Autowired
    DishRepository dishRepository

    MockMvc mockMvc

    def setup()
    {
        mockMvc = MockMvcBuilders
                    .webAppContextSetup(this.wac)
                    .addFilters(springSecurityFilterChain)
                    .build()
    }


    def "Test dishes list api"()
    {
        given:
                dishes.each{d -> dishRepository.save(new DishModel(d))}

        when:
                def response = this.mockMvc.perform(get('https://localhost:8443/api/admin/dishes')
                                                    .with(user('testadmin').password('testpass').roles('ADMIN'))
                                                    .accept(MediaType.APPLICATION_JSON))
                                                    .andDo(print())
        then:
               response
                    .andExpect(status().is(responseStatus.value()))
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               validations.each{ v -> response.andExpect(v) }

        cleanup:
               dishRepository.deleteAll()

        where:
               dishes                     | responseStatus       | validations
               []                         | HttpStatus.OK        | [jsonPath('$').isArray(),jsonPath('$', hasSize(0))]
               ['dish 1']                 | HttpStatus.OK        | [jsonPath('$').isArray(),jsonPath('$', hasSize(1)),jsonPath('$[0].title').value('dish 1')]
               ['dish 1', 'dish 2']       | HttpStatus.OK        | [jsonPath('$').isArray(),jsonPath('$', hasSize(2)),jsonPath('$[*].title').value(contains('dish 1', 'dish 2'))]
    }

    def "Test dish get api"()
    {
        given:
            def ids = dishes ? dishes.collect { d -> dishRepository.save(new DishModel(d)).id } : null
            def id =  ids ? ids[offset] : 1
        when:
            def response = this.mockMvc.perform(get("https://localhost:8443/api/admin/dishes/${id}")
                    .with(user('testadmin').password('testpass').roles('ADMIN'))
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
        then:
            response
                    .andExpect(status().is(responseStatus.value()))
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            if (validations) validations.each{ v -> response.andExpect(v) }

        cleanup:
            dishRepository.deleteAll()

        where:
            dishes                     | offset | responseStatus       | validations
            null                       | 1      | HttpStatus.NOT_FOUND | null
            ['dish 1']                 | 0      | HttpStatus.OK        | [jsonPath('$').exists(),jsonPath('$.title').value('dish 1')]
            ['dish 1', 'dish 2']       | 1      | HttpStatus.OK        | [jsonPath('$').exists(),jsonPath('$.title').value('dish 2')]
    }
}
