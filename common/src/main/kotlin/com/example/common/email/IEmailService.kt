package com.example.api.application.services

interface IEmailService {

    fun sendEmail(to: String, subject: String, message: String)
    fun sendEmail(to: String, subject: String, message: String, fileName: String)
}