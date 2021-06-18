package com.example.shared.services

interface IIdentityService {

    fun getUserIdentity(): String
    fun getUserName(): String
    fun getUserEmail(): String
}