package com.example.domain.exceptions

class NoteDomainException : Exception {

    constructor(message: String)
            : super(message){}

    constructor(message: String, innerException: Exception)
            : super(message, innerException){}
}