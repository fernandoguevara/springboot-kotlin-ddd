package com.example.api.application.requests

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class UpdateNoteRequest (
    @field:NotBlank(message = "title is mandatory")
    @field:Size(min = 5, max = 100)
    var title: String,
    @field:NotBlank(message = "description is mandatory")
    @field:Size(min = 5, max = 200)
    var description: String
        )