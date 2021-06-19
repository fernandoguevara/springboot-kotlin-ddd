package com.example.api.application.services

interface IEmailService {

    fun sendEmail(to: String, subject: String, message: String)
}