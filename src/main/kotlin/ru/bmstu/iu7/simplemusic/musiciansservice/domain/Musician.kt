package ru.bmstu.iu7.simplemusic.musiciansservice.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import ru.bmstu.iu7.simplemusic.musiciansservice.constant.Format
import java.util.*


@Document
data class Musician(
        @Id
        @JsonProperty(value = "id")
        var id: String? = null,

        @Indexed(unique = true)
        @JsonProperty(value = "nickname")
        var nickname: String,

        @Indexed(unique = true)
        @JsonProperty(value = "email")
        var email: String,

        @JsonProperty(value = "fullName")
        var fullName: String,

        @JsonProperty(value = "dateOfBirth")
        @JsonFormat(pattern = Format.DATE)
        var dateOfBirth: Date? = null,


        @JsonProperty(value = "musicalInstruments")
        var musicalInstruments: Set<String> = emptySet(),

        @JsonIgnore
        var active: Boolean = true
)
