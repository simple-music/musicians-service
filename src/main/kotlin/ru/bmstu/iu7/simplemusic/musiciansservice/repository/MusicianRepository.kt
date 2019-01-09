package ru.bmstu.iu7.simplemusic.musiciansservice.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.bmstu.iu7.simplemusic.musiciansservice.domain.Musician
import java.util.*

interface MusicianRepository : MongoRepository<Musician, String> {
    fun findByIdAndActiveIsTrue(id: String): Optional<Musician>
}
