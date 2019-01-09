package ru.bmstu.iu7.simplemusic.musiciansservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MusiciansServiceApplication

fun main(args: Array<String>) {
    runApplication<MusiciansServiceApplication>(*args)
}
