package ru.bmstu.iu7.simplemusic.musiciansservice.exception

open class ServiceException(message: String?) : RuntimeException(message)

class DuplicateException(message: String?) : ServiceException(message)

class NotFoundException(message: String?) : ServiceException(message)
