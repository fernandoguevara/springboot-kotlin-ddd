package com.example.background

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = ["com.example"])
@EntityScan(basePackages = ["com.example.domain"])
@EnableJpaRepositories(basePackages = ["com.example.infrastructure"])
class BackgroundApplication

fun main(args: Array<String>) {
	runApplication<BackgroundApplication>(*args)
}
