package com.example.api.application.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service


@Service
class EmailService(
    private val emailSender: JavaMailSender
) : IEmailService {

    @Value("\${spring.mail.email}")
    private lateinit var from: String

    override fun sendEmail(to: String, subject: String, message: String)
    {
        val email = SimpleMailMessage()
        email.setFrom(from)
        email.setTo(to)
        email.setSubject(subject)
        email.setText(message)
        emailSender.send(email);
    }
}