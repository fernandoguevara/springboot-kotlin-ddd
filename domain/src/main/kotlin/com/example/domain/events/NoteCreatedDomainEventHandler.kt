package com.example.domain.events

import com.example.api.application.services.IEmailService
import com.example.common.identity.IIdentityService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class NoteCreatedDomainEventHandler(
    private val emailService: IEmailService,
    private val identity: IIdentityService
) {

    private val logger: Logger = LoggerFactory.getLogger(NoteCreatedDomainEventHandler::class.java)

    @Async
    @TransactionalEventListener
    fun handle(event: NoteCreatedDomainEvent) {
        logger.info("Note with Id: ${event.Note.id} has been successfully created, sending email")
        val email =  identity.getUserEmail()
        //emailService.sendEmail(email, "Note Created", "You've created a new Note!");
        logger.info("Email Sent for Note: ${event.Note.id}")
    }

}