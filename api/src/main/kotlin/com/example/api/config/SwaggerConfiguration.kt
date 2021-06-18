package com.example.api.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.OAuthBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.SecurityConfiguration
import springfox.documentation.swagger.web.SecurityConfigurationBuilder
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfiguration {
    @Value("\${spring.oauth.authurl}")
    private val authUrl: String? = null

    @Value("\${spring.oauth.tokenurl}")
    private val tokenUrl: String? = null

    @Value("\${spring.oauth.realm}")
    private val realm: String? = null

    @Value("\${spring.oauth.clientid}")
    private val clientId: String? = null
    @Bean
    fun taskApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2) //.groupName(GROUP_NAME)
            .useDefaultResponseMessages(true)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example")) //.paths(regex(ALLOWED_PATHS))
            .build()
            .securitySchemes(listOf(securityScheme()))
            .securityContexts(listOf(securityContext()))
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Notes - note HTTP API")
            .description("The Note Service HTTP API")
            .version("v1").build()
    }

    @Bean
    fun security(): SecurityConfiguration {
        return SecurityConfigurationBuilder.builder()
            .realm(realm)
            .clientId(clientId) //.clientSecret(CLIENT_SECRET)
            //.appName(GROUP_NAME)
            .scopeSeparator(" ")
            .build()
    }

    private fun securityScheme(): SecurityScheme {
        /*GrantType grantType =
                new AuthorizationCodeGrantBuilder()
                        .tokenEndpoint(new TokenEndpoint(tokenUrl, ""))
                        .tokenRequestEndpoint(
                                new TokenRequestEndpoint(authUrl, clientId, ""))
                        .build();*/
        val implicitGrant: GrantType = ImplicitGrant(LoginEndpoint(authUrl), "access_token")
        return OAuthBuilder()
            .name(oauthName)
            .grantTypes(listOf(implicitGrant)) //.scopes(Arrays.asList(scopes()))
            .build()
    }

    private fun scopes(): Array<AuthorizationScope> {
        return arrayOf()
    }

    private fun securityContext(): SecurityContext {
        return SecurityContext.builder()
            .securityReferences(
                listOf(
                    SecurityReference(
                        oauthName,
                        scopes()
                    )
                )
            ) //.forPaths(PathSelectors.regex(allowedPaths))
            .build()
    }

    companion object {
        private const val oauthName = "spring_oauth"
        private const val allowedPaths = "/controllers/.*"
    }
}