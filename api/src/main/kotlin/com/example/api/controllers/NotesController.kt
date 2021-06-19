package com.example.api.controllers

import an.awesome.pipelinr.Pipeline
import com.example.api.application.commands.CreateNoteCommand
import com.example.api.application.dtos.NoteDTO
import com.example.api.application.requests.UpdateNoteRequest
import com.example.domain.exceptions.NoteDomainException
import com.example.infrastructure.repositories.INoteRepository
import org.hibernate.procedure.ParameterMisuseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController()
@RequestMapping("notes")
public class NotesController(
    private val noteRepository: INoteRepository,
    private val pipeline: Pipeline
) {

    @PreAuthorize("hasRole('user')")
    @GetMapping(produces = ["application/json"])
    fun getNotes(): ResponseEntity<List<NoteDTO>> {
        val notes = NoteDTO.fromNotes(noteRepository.getNotes())
        return ResponseEntity(notes, HttpStatus.OK)
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("/{noteId}")
    fun getNote(@PathVariable noteId: String): ResponseEntity<NoteDTO> {
        val note = NoteDTO.fromNote(noteRepository.getNote(noteId))
        return ResponseEntity(note, HttpStatus.OK)
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping
    fun createNote(@RequestBody @Valid command: CreateNoteCommand): ResponseEntity<Any> {
        val commandResult = pipeline.send(command)
        return ResponseEntity(commandResult, HttpStatus.CREATED)
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/{noteId}")
    fun updateNote(@RequestBody @Valid request: UpdateNoteRequest, @RequestParam noteId: String): ResponseEntity<Any> {
        val note = noteRepository.getNote(noteId)
        note.update(request.title, request.description)
        noteRepository.save(note)
        return ResponseEntity("updated", HttpStatus.OK)
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{noteId}")
    fun deleteNote(@RequestParam noteId: String): ResponseEntity<Any> {
        val note = noteRepository.getNote(noteId)
        noteRepository.delete(note)
        return ResponseEntity("deleted", HttpStatus.OK)
    }
}