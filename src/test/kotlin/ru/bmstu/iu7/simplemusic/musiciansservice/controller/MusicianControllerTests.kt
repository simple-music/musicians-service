package ru.bmstu.iu7.simplemusic.musiciansservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.bson.types.ObjectId
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import ru.bmstu.iu7.simplemusic.musiciansservice.domain.Musician
import ru.bmstu.iu7.simplemusic.musiciansservice.model.MusicianUpdate
import ru.bmstu.iu7.simplemusic.musiciansservice.model.NewMusician
import ru.bmstu.iu7.simplemusic.musiciansservice.service.MusicianService


@RunWith(SpringRunner::class)
@WebMvcTest(controllers = [MusicianController::class], secure = false)
class MusicianControllerTests {
    @Autowired
    private val mockMvc: MockMvc? = null

    @MockBean
    private val mockService: MusicianService? = null

    @Test
    fun createProfile() {
        val newMusician = this.generateNewMusician()
        val newMusicianStr = this.mapObject(newMusician)

        val musician = this.generateMusician()
        val musicianStr = this.mapObject(musician)

        Mockito
                .`when`(this.mockService!!.addMusician(this.any()))
                .thenReturn(musician)

        this.mockMvc!!
                .perform(MockMvcRequestBuilders
                        .post("/musicians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newMusicianStr))
                .andExpect(MockMvcResultMatchers
                        .status().isCreated)
                .andExpect(MockMvcResultMatchers
                        .content().string(musicianStr))
    }

    @Test
    fun getProfile() {
        val id = this.generateId()

        val musician = this.generateMusician(id)
        val musicianStr = this.mapObject(musician)

        Mockito
                .`when`(this.mockService!!.getMusician(id))
                .thenReturn(musician)

        this.mockMvc!!
                .perform(MockMvcRequestBuilders
                        .get("/musicians/$id"))
                .andExpect(MockMvcResultMatchers
                        .status().isOk)
                .andExpect(MockMvcResultMatchers
                        .content().string(musicianStr))
    }

    @Test
    fun updateProfile() {
        val musicianUpdate = this.generateMusicianUpdate()
        val musicianUpdateStr = this.mapObject(musicianUpdate)

        val id = this.generateId()

        val musician = this.generateMusician(id)
        val musicianStr = this.mapObject(musician)

        Mockito
                .`when`(this.mockService!!.updateMusician(Mockito.anyString(), this.any()))
                .thenReturn(musician)
        this.mockMvc!!
                .perform(MockMvcRequestBuilders
                        .patch("/musicians/$id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(musicianUpdateStr))
                .andExpect(MockMvcResultMatchers
                        .status().isOk)
                .andExpect(MockMvcResultMatchers
                        .content().string(musicianStr))

        Mockito
                .`when`(this.mockService.updateMusician(Mockito.anyString(), this.any()))
                .thenReturn(null)
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/musicians/$id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(musicianUpdateStr))
                .andExpect(MockMvcResultMatchers
                        .status().isNotModified)
    }

    @Test
    fun deleteProfile() {
        val id = this.generateId()

        Mockito
                .doNothing().`when`(this.mockService)
                ?.deleteMusician(id, false)

        this.mockMvc!!
                .perform(MockMvcRequestBuilders
                        .delete("/musicians/$id"))
                .andExpect(MockMvcResultMatchers
                        .status().isNoContent)
    }

    private fun mapObject(obj: Any): String {
        return ObjectMapper().writeValueAsString(obj)
    }

    private fun generateNewMusician(): NewMusician {
        val id = this.generateId()
        return NewMusician(
                nickname = this.generateNickname(id),
                email = this.generateEmail(id),
                firstName = this.generateFirstName(id),
                lastName = this.generateLastName(id)
        )
    }

    private fun generateMusician(id: String = this.generateId()): Musician {
        return Musician(
                id = id,
                nickname = this.generateNickname(id),
                email = this.generateEmail(id),
                firstName = this.generateFirstName(id),
                lastName = this.generateLastName(id)
        )
    }

    private fun generateMusicianUpdate(): MusicianUpdate {
        return MusicianUpdate(
                email = this.generateEmail()
        )
    }

    private fun generateId(): String {
        return ObjectId.get().toHexString()
    }

    private fun generateNickname(id: String = this.generateId()): String {
        return "musician$id"
    }

    private fun generateEmail(id: String = this.generateId()): String {
        return "musician$id@example.com"
    }

    private fun generateFirstName(id: String = this.generateId()): String {
        return "FirstName$id"
    }

    private fun generateLastName(id: String = this.generateId()): String {
        return "LastName$id"
    }

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T
}
