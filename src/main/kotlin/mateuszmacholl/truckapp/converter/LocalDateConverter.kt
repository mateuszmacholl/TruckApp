package mateuszmacholl.truckapp.converter

import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


@Service
class LocalDateConverter: Converter<String, LocalDate> {
    override fun convert(from: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("yyy-MM-dd", Locale.ENGLISH)
        try{
            return LocalDate.parse(from, formatter)
        } catch (ex: Exception){
            throw IllegalArgumentException("Incorrect date", ex)
        }
    }
}