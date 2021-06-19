package com.example.domain.aggregatesmodel

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import java.time.Instant
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
public class Email {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) Postgres
    @GenericGenerator(name = "generator", strategy = "uuid2", parameters = [])
    @GeneratedValue(generator = "generator")
    @Column(name = "id" , columnDefinition="uniqueidentifier")
    var id: UUID? = null

    private var action: String

    @CreatedDate
    private var createdAt: Instant

    constructor(action: String) {
        this.action = action
        this.createdAt = Instant.now()
    }
}