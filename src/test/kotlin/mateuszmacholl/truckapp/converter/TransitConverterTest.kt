package mateuszmacholl.truckapp.converter

import mateuszmacholl.truckapp.dao.DistanceDao
import mateuszmacholl.truckapp.dto.TransitDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.time.LocalDate

class TransitConverterTest {
    private val distanceDao = Mockito.mock(DistanceDao::class.java)
    private val transitConverter = TransitConverter(distanceDao)
    private val from = TransitDto(
            450.0,
            LocalDate.of(2018, 11, 30),
            "Wejherowo",
            "Gdynia"
    )

    @BeforeEach
    fun init(){
        Mockito.`when`(distanceDao.getDistance(from.sourceAddress, from.destinationAddress)).thenReturn(23.0)
    }

    @Test
    fun convert() {
        //when
        val fileEntity = transitConverter.convert(from)
        //then
        assertEquals(from.date, fileEntity.date)
        assertEquals(from.price, fileEntity.price)
        assertEquals(23.0, fileEntity.distance)
    }
}