package com.example.api.config

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = [KeycloakSecurityComponents::class])
@EnableGlobalMethodSecurity(prePostEnabled = true)
internal class SecurityConfig : KeycloakWebSecurityConfigurerAdapter() {
    // Submits the KeycloakAuthenticationProvider to the AuthenticationManager
    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        val keycloakAuthenticationProvider = keycloakAuthenticationProvider()
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(SimpleAuthorityMapper())
        auth.authenticationProvider(keycloakAuthenticationProvider)
    }

    @Bean
    fun KeycloakConfigResolver(): KeycloakSpringBootConfigResolver {
        return KeycloakSpringBootConfigResolver()
    }

    // Specifies the session authentication strategy
    @Bean
    override fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy {
        return RegisterSessionAuthenticationStrategy(SessionRegistryImpl())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
            .anyRequest()
            .permitAll()
        http.addFilterAfter(
            keycloakAuthenticatedActionsRequestFilter(),
            SecurityContextHolderAwareRequestFilter::class.java
        )
    }
}