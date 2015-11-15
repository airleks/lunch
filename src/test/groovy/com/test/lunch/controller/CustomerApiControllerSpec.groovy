package com.test.lunch.controller

import com.test.lunch.ApplicationConfig
import com.test.lunch.SecurityConfig
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

/**
 *  CustomerApiController tests
 */
@ContextConfiguration(loader = SpringApplicationContextLoader,
        classes = [ApplicationConfig,SecurityConfig])
@WebAppConfiguration
class CustomerApiControllerSpec extends Specification
{
}
