package ru.bmstu.iu7.simplemusic.musiciansservice.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import ru.bmstu.iu7.simplemusic.musiciansservice.domain.Musician
import ru.bmstu.iu7.simplemusic.musiciansservice.exception.DuplicateException
import ru.bmstu.iu7.simplemusic.musiciansservice.exception.NotFoundException
import ru.bmstu.iu7.simplemusic.musiciansservice.model.MusicianUpdate
import ru.bmstu.iu7.simplemusic.musiciansservice.model.NewMusician
import ru.bmstu.iu7.simplemusic.musiciansservice.repository.MusicianRepository

@Service
class MusicianServiceImpl(@Autowired private val musicianRepository: MusicianRepository) : MusicianService {
    override fun addMusician(newMusician: NewMusician): Musician {
        var musician = Musician(
                nickname = newMusician.nickname,
                email = newMusician.email,
                fullName = newMusician.fullName,
                dateOfBirth = newMusician.dateOfBirth,
                musicalInstruments = newMusician.musicalInstruments ?: emptySet()
        )

        try {
            musician = this.musicianRepository.save(musician)
        } catch (exception: DuplicateKeyException) {
            throw DuplicateException("musician duplicate")
        }

        return musician
    }

    override fun findMusician(nickname: String): Musician {
        return musicianRepository
                .findByNicknameAndActiveIsTrue(nickname)
                .orElseThrow {
                    NotFoundException("musician not found")
                }
    }

    override fun getMusician(musicianId: String): Musician {
        return musicianRepository
                .findByIdAndActiveIsTrue(musicianId)
                .orElseThrow {
                    NotFoundException("musician not found")
                }
    }

    override fun updateMusician(musicianId: String, musicianUpdate: MusicianUpdate): Musician? {
        var musician = this.musicianRepository
                .findByIdAndActiveIsTrue(musicianId)
                .orElseThrow {
                    NotFoundException("musician not found")
                }

        var modified = false

        if (musicianUpdate.email != null && musician.email != musicianUpdate.email) {
            musician.email = musicianUpdate.email
            modified = true
        }
        if (musicianUpdate.fullName != null && musician.fullName != musicianUpdate.fullName) {
            musician.fullName = musicianUpdate.fullName
            modified = true
        }
        if ((musician.dateOfBirth == null && musicianUpdate.dateOfBirth != null) ||
                (musicianUpdate.dateOfBirth != null && musician.dateOfBirth != musicianUpdate.dateOfBirth)) {
            musician.dateOfBirth = musicianUpdate.dateOfBirth
            modified = true
        }
        if (musicianUpdate.musicalInstruments != null) {
            musician.musicalInstruments = musicianUpdate.musicalInstruments
            modified = true
        }

        try {
            musician = this.musicianRepository.save(musician)
        } catch (exception: DuplicateKeyException) {
            throw DuplicateException("musician duplicate")
        }

        return if (modified) musician else null
    }

    override fun deleteMusician(musicianId: String, permanently: Boolean) {
        val musician = this.musicianRepository
                .findByIdAndActiveIsTrue(musicianId)
                .orElseThrow {
                    NotFoundException("musician not found")
                }
        if (permanently) {
            musician.active = false
            this.musicianRepository.save(musician)
        } else {
            this.musicianRepository.delete(musician)
        }
    }
}
