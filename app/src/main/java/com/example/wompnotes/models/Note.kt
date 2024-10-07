package com.example.wompnotes.models

import java.util.*

enum class NoteType {
    NOTE, TASK
}

data class Note(
    val id: UUID = UUID.randomUUID(),
    var title: String,
    var description: String,
    var noteType: NoteType = NoteType.NOTE,
    var dateCreated: Date = Date(),
    var dueDate: Date? = null,
    var reminders: List<Date> = emptyList(),
    var attachments: List<Attachment> = emptyList()
)

data class Attachment(
    val id: UUID = UUID.randomUUID(),
    val type: AttachmentType,
    val uri: String,
    val description: String
)

enum class AttachmentType {
    IMAGE, VIDEO, AUDIO
}