package com.example.domain.aggregatesmodel

import com.example.domain.events.NoteCreatedDomainEvent
import com.example.domain.exceptions.NoteDomainException
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.AbstractAggregateRoot
import java.time.Instant
import java.time.ZoneOffset
import java.util.*
import javax.persistence.*


@Entity
public class Note : AbstractAggregateRoot<Note> {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) Postgres
    @GenericGenerator(name = "generator", strategy = "uuid2", parameters = [])
    @GeneratedValue(generator = "generator")
    @Column(name = "id" , columnDefinition="uniqueidentifier")
    var id: UUID? = null

    @Column(name = "user_id" , columnDefinition="uniqueidentifier")
    var userId: UUID? = null
        private set

    var title: String
        private set

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

    constructor(userId: UUID, title: String, description: String) {
        this.userId = userId
        this.title = title
        this.description = description

        val now = Instant.now()
        if (IsTooLate(now)) throw NoteDomainException("Its too late go back to sleep :(");
        this.createdAt = now
        this.modifiedAt = now
    }

    public fun addEmail(action: String) {
        val email = Email(action)
        this.emails.add(email)
        registerEvent(NoteCreatedDomainEvent(this))
    }

    public fun update(title: String, description: String) {
        this.title = title
        this.description = description
    }

    private fun IsTooLate(now: Instant): Boolean {
        val hour = now.atZone(ZoneOffset.UTC).hour
        return hour in 12..6
    }
}