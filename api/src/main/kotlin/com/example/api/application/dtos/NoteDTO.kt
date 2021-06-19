package com.example.api.application.dtos

import com.example.domain.aggregatesmodel.Note

class NoteDTO{

    lateinit var userId: String
    lateinit var title: String
    lateinit var description: String

    companion object{

        fun fromNote(note: Note): NoteDTO{
            val dto = NoteDTO()
            //dto.userId = dto.userId.toString() // postgres
            dto.userId = note.userId.toString().uppercase()
            dto.title = note.title
            dto.description = note.description

            return dto
        }

        fun fromNotes(notes: List<Note>): List<NoteDTO> {
            return notes.map {
                val dto = NoteDTO()
                //dto.userId = it.userId.toString() // postgres
                dto.userId = it.userId.toString().uppercase()
                dto.title = it.title
                dto.description = it.description
                dto
            }
        }
    }
}