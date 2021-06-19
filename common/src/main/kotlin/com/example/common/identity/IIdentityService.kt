package com.example.common.identity

interface IIdentityService {

    fun getUserIdentity(): String
    fun getUserName(): String
    fun getUserEmail(): String
}