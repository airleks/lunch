package com.test.lunch.controller

import com.test.lunch.ApplicationConfig
import com.test.lunch.SecurityConfig
import com.test.lunch.model.RestaurantModel
import com.test.lunch.repository.RestaurantRepository
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 *  AdminApiRestaurantController tests
 */
@ContextConfiguration(loader = SpringApplicationContextLoader,
        classes = [ApplicationConfig,SecurityConfig])
@WebAppConfiguration
class AdminApiRestaurantControllerSpec extends Specification
{
    @Autowired
    WebApplicationContext wac

    @Autowired
    Filter springSecurityFilterChain

    @Autowired
    RestaurantRepository restaurantRepository

    MockMvc mockMvc

    def setup()
    {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .addFilters(springSecurityFilterChain)
                .build()
    }

    def "Test restaurants list api"()
    {
        given:
            restaurants.each{r -> restaurantRepository.save(new RestaurantModel(r))}

        when:
            def response = this.mockMvc.perform(new MockHttpServletRequestBuilder(requestType, url)
                    .with(user('testadmin').password('testpass').roles('ADMIN'))
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())

        then:
            response
                    .andExpect(status().is(responseStatus.value()))
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            validations.each{ v -> response.andExpect(v) }

        cleanup:
            restaurantRepository.deleteAll()

        where:
            restaurants                | url                         | requestType       |  responseStatus       | validations
            []                         | '/api/admin/restaurants'    | HttpMethod.GET    |  HttpStatus.OK        | [jsonPath('$').isArray(),jsonPath('$', hasSize(0))]
            ['res 1']                  | '/api/admin/restaurants'    | HttpMethod.GET    |  HttpStatus.OK        | [jsonPath('$').isArray(),jsonPath('$', hasSize(1)),jsonPath('$[0].name').value('res 1')]
            ['res 1', 'res 2']         | '/api/admin/restaurants'    | HttpMethod.GET    |  HttpStatus.OK        | [jsonPath('$').isArray(),jsonPath('$', hasSize(2)),jsonPath('$[*].name').value(contains('res 1', 'res 2'))]
    }

}
