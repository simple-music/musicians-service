package ru.bmstu.iu7.simplemusic.musiciansservice.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.bmstu.iu7.simplemusic.musiciansservice.domain.Musician
import ru.bmstu.iu7.simplemusic.musiciansservice.model.MusicianUpdate
import ru.bmstu.iu7.simplemusic.musiciansservice.model.NewMusician
import ru.bmstu.iu7.simplemusic.musiciansservice.repository.MusicianRepository

@Service
@Transactional
class MusicianServiceImpl(@Autowired private val musicianRepository: MusicianRepository) : MusicianService {

    override fun addMusician(newMusician: NewMusician): Musician {
        TODO("not implemented")
    }

    override fun getMusician(musicianId: String): Musician {
        TODO("not implemented")
    }

    override fun updateMusician(musicianId: String, musicianUpdate: MusicianUpdate): Musician? {
        TODO("not implemented")
    }

    override fun deleteMusician(musicianId: String) {
        TODO("not implemented")
    }
}
