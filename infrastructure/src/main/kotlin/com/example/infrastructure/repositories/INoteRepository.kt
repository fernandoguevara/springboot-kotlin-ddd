package com.example.infrastructure.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*
import com.example.domain.aggregatesmodel.Note

@Repository
public interface  INoteRepository : JpaRepository<Note, UUID> {

    @Query("SELECT * FROM note", nativeQuery = true)
    public fun getNotes(): List<Note>

    @Query("SELECT * FROM note WHERE id = ?", nativeQuery = true)
    public fun getNote(noteId: String): Note
}