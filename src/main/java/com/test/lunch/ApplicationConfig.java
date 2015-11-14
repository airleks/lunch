package com.test.lunch;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Application configuration
 */
@SpringBootApplication
@Import(SecurityConfig.class)
public class ApplicationConfig extends WebMvcConfigurerAdapter
{
}
