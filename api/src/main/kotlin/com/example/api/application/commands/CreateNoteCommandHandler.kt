package com.example.api.application.commands

import an.awesome.pipelinr.Command
import com.example.api.application.dtos.NoteDTO
import com.example.domain.aggregatesmodel.Note
import com.example.infrastructure.repositories.INoteRepository
import com.example.common.identity.IIdentityService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*


@Component
class CreateNoteCommandHandler(
    private val noteRepository: INoteRepository,
    private val identity: IIdentityService
) : Command.Handler<CreateNoteCommand, NoteDTO> {
    private val logger: Logger = LoggerFactory.getLogger(CreateNoteCommandHandler::class.java)

    override fun handle(command: CreateNoteCommand): NoteDTO {
        //val userId = UUID.randomUUID()
        val userId = UUID.fromString(identity.getUserIdentity())
        val note = Note(userId, command.title, command.description)
        note.addEmail("Note created")

        logger.info("Creating Note - Note: $note")
        noteRepository.save(note)
        logger.info("Note Created - Note: $note")
        return NoteDTO.fromNote(note)
    }
}