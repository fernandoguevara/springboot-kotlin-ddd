package com.example.api.application.commands

import an.awesome.pipelinr.Command
import com.example.api.application.dtos.NoteDTO
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class CreateNoteCommand(
    @field:NotBlank(message = "title is mandatory")
    @field:Size(min = 5, max = 100)
    var title: String,
    @field:NotBlank(message = "description is mandatory")
    @field:Size(min = 5, max = 200)
    var description: String
        ) : Command<NoteDTO> {

}