package ru.bmstu.iu7.simplemusic.musiciansservice.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import ru.bmstu.iu7.simplemusic.musiciansservice.domain.Musician
import ru.bmstu.iu7.simplemusic.musiciansservice.model.MusicianUpdate
import ru.bmstu.iu7.simplemusic.musiciansservice.model.NewMusician
import ru.bmstu.iu7.simplemusic.musiciansservice.service.MusicianService

@RestController
@RequestMapping(value = ["/musicians"])
class MusicianController(@Autowired private val musicianService: MusicianService) {
    @PostMapping
    fun addMusician(@RequestBody newMusician: NewMusician): ResponseEntity<Musician> {
        val musician = this.musicianService.addMusician(newMusician)
        val uri = MvcUriComponentsBuilder
                .fromController(this::class.java).path("/{id}")
                .buildAndExpand(musician.id).toUri()
        return ResponseEntity.created(uri).body(musician)
    }

    @GetMapping
    fun findMusician(@RequestParam(value = "nickname",
            required = true) nickname: String): ResponseEntity<Musician> {
        val musician = this.musicianService.findMusician(nickname)
        return ResponseEntity.ok(musician)
    }

    @GetMapping(value = ["/{id}"])
    fun getMusician(@PathVariable(value = "id") musicianId: String): ResponseEntity<Musician> {
        val musician = this.musicianService.getMusician(musicianId)
        return ResponseEntity.ok(musician)
    }

    @PatchMapping(value = ["/{id}"])
    fun updateMusician(@PathVariable(value = "id") musicianId: String,
                       @RequestBody musicianUpdate: MusicianUpdate): ResponseEntity<Musician> {
        val musician = this.musicianService.updateMusician(musicianId, musicianUpdate)
        return if (musician == null) {
            ResponseEntity.status(HttpStatus.NOT_MODIFIED.value()).build()
        } else {
            ResponseEntity.ok(musician)
        }
    }

    @DeleteMapping(value = ["/{id}"])
    fun deleteMusician(@PathVariable(value = "id") musicianId: String,
                       @RequestParam(value = "permanently", required = false,
                               defaultValue = "false") permanently: Boolean): ResponseEntity<Musician> {
        this.musicianService.deleteMusician(musicianId, permanently)
        return ResponseEntity.noContent().build()
    }
}
