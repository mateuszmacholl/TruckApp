package mateuszmacholl.truckapp.converter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LocalDateConverterTest {
    private val dateTimeConverter = LocalDateConverter()
    private val from = "2018-11-30"

    @Test
    fun convert(){
        //when
        val localDate = dateTimeConverter.convert(from)
        //then
        assertEquals(from, localDate.toString())
    }
}