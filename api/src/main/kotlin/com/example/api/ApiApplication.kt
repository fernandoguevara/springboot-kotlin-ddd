package com.example.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.context.request.RequestContextListener
import springfox.documentation.swagger2.annotations.EnableSwagger2


@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = ["com.example"])
@EntityScan(basePackages = ["com.example.domain"])
@EnableJpaRepositories(basePackages = ["com.example.infrastructure"])
class ApiApplication

fun main(args: Array<String>) {
	runApplication<ApiApplication>(*args)
}