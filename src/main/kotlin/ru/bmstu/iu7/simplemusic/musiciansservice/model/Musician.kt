package ru.bmstu.iu7.simplemusic.musiciansservice.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bmstu.iu7.simplemusic.musiciansservice.constant.Format
import java.util.*

data class NewMusician(
        @JsonProperty(value = "nickname", required = true)
        val nickname: String,

        @JsonProperty(value = "email", required = true)
        val email: String,

        @JsonProperty(value = "fullName", required = true)
        val fullName: String,

        @JsonProperty(value = "dateOfBirth", required = false)
        @JsonFormat(pattern = Format.DATE)
        val dateOfBirth: Date? = null,

        @JsonProperty(value = "musicalInstruments", required = false)
        val musicalInstruments: Set<String>? = emptySet()
)

data class MusicianUpdate(
        @JsonProperty(value = "email", required = false)
        val email: String? = null,

        @JsonProperty(value = "fullName", required = false)
        val fullName: String? = null,

        @JsonProperty(value = "dateOfBirth", required = false)
        @JsonFormat(pattern = Format.DATE)
        val dateOfBirth: Date? = null,

        @JsonProperty(value = "musicalInstruments", required = false)
        val musicalInstruments: Set<String>? = null
)
