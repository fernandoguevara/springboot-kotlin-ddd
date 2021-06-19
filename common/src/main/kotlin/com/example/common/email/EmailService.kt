package com.example.common.email

import com.example.api.application.services.IEmailService
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ByteArrayResource
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import javax.mail.internet.MimeMessage





@Service
class EmailService(
    private val emailSender: JavaMailSender
) : IEmailService {

    @Value("\${spring.mail.email}")
    private lateinit var from: String

    override fun sendEmail(to: String, subject: String, message: String) {
        val email = SimpleMailMessage()
        email.setFrom(from)
        email.setTo(to)
        email.setSubject(subject)
        email.setText(message)
        emailSender.send(email);
    }

    override fun sendEmail(to: String, subject: String, message: String, fileName: String) {
        val path: Path = Paths.get(fileName)
        val content: ByteArray = Files.readAllBytes(path)

        val mimeMessage: MimeMessage = emailSender.createMimeMessage()
        val email = MimeMessageHelper(mimeMessage, true)
        email.setFrom(from)
        email.setTo(to)
        email.setSubject(subject)
        email.setText(message)
        email.addAttachment(fileName, ByteArrayResource(content))
        emailSender.send(mimeMessage)
    }
}