package com.test.mvn.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.resource.PathResourceResolver

@Configuration
@EnableWebMvc
class MvcConfig : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry
            .addResourceHandler("assets/**", "web/**")
//            .addResourceLocations("file:web/", "file:web/assets/", "file:assets/")
            .addResourceLocations("classpath:web/", "classpath:web/assets/", "classpath:assets/")
            .setCachePeriod(3600 * 24 * 30)
            .resourceChain(true)
            .addResolver(PathResourceResolver())
    }
}