package ru.bmstu.iu7.simplemusic.musiciansservice.service

import ru.bmstu.iu7.simplemusic.musiciansservice.domain.Musician
import ru.bmstu.iu7.simplemusic.musiciansservice.model.MusicianUpdate
import ru.bmstu.iu7.simplemusic.musiciansservice.model.NewMusician

interface MusicianService {
    fun addMusician(newMusician: NewMusician): Musician
    fun findMusician(nickname: String): Musician
    fun getMusician(musicianId: String): Musician
    fun updateMusician(musicianId: String, musicianUpdate: MusicianUpdate): Musician?
    fun deleteMusician(musicianId: String, permanently: Boolean)
}
