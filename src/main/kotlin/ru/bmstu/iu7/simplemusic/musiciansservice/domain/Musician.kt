package ru.bmstu.iu7.simplemusic.musiciansservice.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import ru.bmstu.iu7.simplemusic.musiciansservice.model.MusicianSerializer
import java.util.*


@Document
@JsonSerialize(using = MusicianSerializer::class)
data class Musician(
        @Id
        var id: String? = null,

        @Indexed(unique = true)
        var nickname: String,

        @Indexed(unique = true)
        var email: String,

        var firstName: String,
        var lastName: String,

        var dateOfBirth: Date? = null,

        var active: Boolean = true
)
