package com.example.common.identity

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.keycloak.representations.AccessToken
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest


@Service
class IdentityService(
    private val http: HttpServletRequest
) : IIdentityService {

    private fun getAccessToken(): AccessToken{
        val token = this.http.userPrincipal as KeycloakAuthenticationToken
        val accessToken = token.account.keycloakSecurityContext.token
        return accessToken
    }

    override fun getUserIdentity(): String {
        return this.getAccessToken().subject
    }

    override fun getUserName(): String {
        return this.getAccessToken().preferredUsername
    }

    override fun getUserEmail(): String {
       return this.getAccessToken().email
    }
}