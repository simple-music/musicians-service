package ru.bmstu.iu7.simplemusic.musiciansservice.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.bmstu.iu7.simplemusic.musiciansservice.domain.Musician
import ru.bmstu.iu7.simplemusic.musiciansservice.model.MusicianUpdate
import ru.bmstu.iu7.simplemusic.musiciansservice.model.NewMusician
import ru.bmstu.iu7.simplemusic.musiciansservice.service.MusicianService

@RestController("/musicians")
class MusicianController(@Autowired private val musicianService: MusicianService) {

    @PostMapping
    fun addMusician(@RequestBody newMusician: NewMusician): ResponseEntity<Musician> {
        TODO("not implemented")
    }

    @GetMapping("/{id}")
    fun getMusician(@PathVariable("id") musicianId: String): ResponseEntity<Musician> {
        TODO("not implemented")
    }

    @PatchMapping("/{id}")
    fun updateMusician(@PathVariable("id") musicianId: String,
                       @RequestBody musicianUpdate: MusicianUpdate): ResponseEntity<Musician?> {
        TODO("not implemented")
    }

    @DeleteMapping("/{id}")
    fun deleteMusician(@PathVariable("id") musicianId: String): ResponseEntity<Musician?> {
        TODO("not implemented")
    }
}
