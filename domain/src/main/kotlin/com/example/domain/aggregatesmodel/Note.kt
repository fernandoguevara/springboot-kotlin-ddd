package com.example.domain.aggregatesmodel

import com.example.domain.events.NoteCreatedDomainEvent
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.AbstractAggregateRoot
import java.time.Instant
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
public class Note : AbstractAggregateRoot<Note>
{
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) Postgres
    @GenericGenerator(name = "generator", strategy = "uuid2", parameters = [])
    @GeneratedValue(generator = "generator")
    @Column(name = "id" , columnDefinition="uniqueidentifier")
    var id: UUID? = null

    @Column(name = "user_id" , columnDefinition="uniqueidentifier")
    var userId: UUID? = null
        private set

    //@NotBlank(message = "title is mandatory")
    //@Size(min = 5, max = 100)
    var title: String
        private set

    //@NotBlank(message = "description is mandatory")
    //@Size(min = 5, max = 200)
    var description: String
        private set

    @CreatedDate
    private var createdAt: Instant

    @LastModifiedDate
    private var modifiedAt: Instant

    @OneToMany(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "note_id", nullable = false)
    private val emails = mutableListOf<Email>()

    //val emails get() = _emails.toList()

    constructor(userId: UUID, title: String, description: String)
    {
        this.userId = userId
        this.title = title
        this.description = description
        this.createdAt = Instant.now()
        this.modifiedAt = Instant.now()
    }

    public fun addEmail(action: String)
    {
        val email = Email(action)
        this.emails.add(email)
        registerEvent(NoteCreatedDomainEvent(this))
    }

    public fun update(title: String, description: String)
    {
        this.title = title
        this.description = description
    }
}