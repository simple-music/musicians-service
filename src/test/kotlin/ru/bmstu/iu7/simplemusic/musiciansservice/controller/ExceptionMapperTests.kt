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
import ru.bmstu.iu7.simplemusic.musiciansservice.exception.DuplicateException
import ru.bmstu.iu7.simplemusic.musiciansservice.exception.NotFoundException
import ru.bmstu.iu7.simplemusic.musiciansservice.model.Error
import ru.bmstu.iu7.simplemusic.musiciansservice.model.NewMusician
import ru.bmstu.iu7.simplemusic.musiciansservice.service.MusicianService

@RunWith(SpringRunner::class)
@WebMvcTest(controllers = [MusicianController::class], secure = false)
class ExceptionMapperTests {
    @Autowired
    private val mockMvc: MockMvc? = null

    @MockBean
    private val mockService: MusicianService? = null

    private val errorMessage = "error"
    private val errorResponseBody = this.mapObject(Error(this.errorMessage))

    @Test
    fun createMusician() {
        val newMusician = this.generateNewMusician()
        val newMusicianStr = this.mapObject(newMusician)

        Mockito
                .`when`(this.mockService!!.addMusician(this.any()))
                .thenThrow(DuplicateException(this.errorMessage))

        this.mockMvc!!
                .perform(MockMvcRequestBuilders
                        .post("/musicians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newMusicianStr))
                .andExpect(MockMvcResultMatchers
                        .status().isConflict)
                .andExpect(MockMvcResultMatchers
                        .content().string(this.errorResponseBody))
    }

    @Test
    fun getMusician() {
        val id = this.generateId()

        Mockito
                .`when`(this.mockService!!.getMusician(id))
                .thenThrow(NotFoundException(this.errorMessage))

        this.mockMvc!!
                .perform(MockMvcRequestBuilders
                        .get("/musicians/$id"))
                .andExpect(MockMvcResultMatchers
                        .status().isNotFound)
                .andExpect(MockMvcResultMatchers
                        .content().string(this.errorResponseBody))
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
