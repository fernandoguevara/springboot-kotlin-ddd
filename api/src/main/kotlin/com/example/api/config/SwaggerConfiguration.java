package com.example.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${spring.oauth.authurl}")
    private String authUrl;

    @Value("${spring.oauth.tokenurl}")
    private String tokenUrl;

    @Value("${spring.oauth.realm}")
    private String realm;

    @Value("${spring.oauth.clientid}")
    private String clientId;

    private static final String oauthName = "spring_oauth";
    private static final String allowedPaths = "/controllers/.*";

    @Bean
    public Docket taskApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.groupName(GROUP_NAME)
                .useDefaultResponseMessages(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage( "com.example" ))
                //.paths(regex(ALLOWED_PATHS))
                .build()
                .securitySchemes(Arrays.asList(securityScheme()))
                .securityContexts(Arrays.asList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new
                ApiInfoBuilder()
                .title("Notes - note HTTP API")
                .description("The Note Service HTTP API")
                .version("v1").build();
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .realm(realm)
                .clientId(clientId)
                //.clientSecret(CLIENT_SECRET)
                //.appName(GROUP_NAME)
                .scopeSeparator(" ")
                .build();
    }

    private SecurityScheme securityScheme() {
        /*GrantType grantType =
                new AuthorizationCodeGrantBuilder()
                        .tokenEndpoint(new TokenEndpoint(tokenUrl, ""))
                        .tokenRequestEndpoint(
                                new TokenRequestEndpoint(authUrl, clientId, ""))
                        .build();*/

        ImplicitGrant implicitGrant = new ImplicitGrant(new LoginEndpoint(authUrl),"access_token");

        SecurityScheme oauth =
                new OAuthBuilder()
                        .name(oauthName)
                        .grantTypes(Arrays.asList(implicitGrant))
                        //.scopes(Arrays.asList(scopes()))
                        .build();
        return oauth;
    }

    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = {
                //new AuthorizationScope("user", "for CRUD operations"),
                //new AuthorizationScope("read", "for read operations"),
                //new AuthorizationScope("write", "for write operations")
        };
        return scopes;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(new SecurityReference(oauthName, scopes())))
                //.forPaths(PathSelectors.regex(allowedPaths))
                .build();
    }
}