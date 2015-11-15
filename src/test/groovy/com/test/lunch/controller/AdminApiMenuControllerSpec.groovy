package com.test.lunch.controller

import com.test.lunch.ApplicationConfig
import com.test.lunch.SecurityConfig
import com.test.lunch.repository.MenuRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import javax.servlet.Filter

/**
 *  AdminApiMenuController tests
 */
@ContextConfiguration(loader = SpringApplicationContextLoader,
        classes = [ApplicationConfig,SecurityConfig])
@WebAppConfiguration
class AdminApiMenuControllerSpec extends Specification
{
    @Autowired
    WebApplicationContext wac

    @Autowired
    Filter springSecurityFilterChain

    @Autowired
    MenuRepository menuRepository

    MockMvc mockMvc

    def setup()
    {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .addFilters(springSecurityFilterChain)
                .build()
    }

}
