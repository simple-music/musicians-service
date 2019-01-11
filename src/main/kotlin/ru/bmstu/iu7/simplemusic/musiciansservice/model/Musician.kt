package ru.bmstu.iu7.simplemusic.musiciansservice.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.fasterxml.jackson.databind.util.StdDateFormat
import ru.bmstu.iu7.simplemusic.musiciansservice.domain.Musician
import java.util.*

data class NewMusician(
        @JsonProperty(value = "nickname", required = true)
        val nickname: String,

        @JsonProperty(value = "email", required = true)
        val email: String,

        @JsonProperty(value = "firstName", required = true)
        val firstName: String,

        @JsonProperty(value = "lastName", required = true)
        val lastName: String,

        @JsonProperty(value = "dateOfBirth", required = false)
        val dateOfBirth: Date? = null
)

data class MusicianUpdate(
        @JsonProperty(value = "email", required = false)
        val email: String? = null,

        @JsonProperty(value = "firstName", required = false)
        val firstName: String? = null,

        @JsonProperty(value = "lastName", required = false)
        val lastName: String? = null,

        @JsonProperty(value = "dateOfBirth", required = false)
        val dateOfBirth: Date? = null
)

class MusicianSerializer @JvmOverloads constructor(t: Class<Musician>? = null) : StdSerializer<Musician>(t) {
    private val objectMapper: ObjectMapper = ObjectMapper()

    init {
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        this.objectMapper.dateFormat = StdDateFormat().withColonInTimeZone(true)
    }

    override fun serialize(value: Musician, jgen: JsonGenerator, provider: SerializerProvider) {
        jgen.writeStartObject()
        jgen.writeStringField("id", value.id)
        jgen.writeStringField("nickname", value.nickname)
        jgen.writeStringField("email", value.email)
        jgen.writeStringField("firstName", value.firstName)
        jgen.writeStringField("lastName", value.lastName)
        if (value.dateOfBirth != null) {
            jgen.writeStringField("dateOfBirth",
                    this.objectMapper.writeValueAsString(value.dateOfBirth)
            )
        } else {
            jgen.writeNullField("dateOfBirth")
        }
        jgen.writeEndObject()
    }
}
