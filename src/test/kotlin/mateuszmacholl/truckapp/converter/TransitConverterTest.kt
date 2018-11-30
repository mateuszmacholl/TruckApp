package mateuszmacholl.truckapp.converter

import mateuszmacholl.truckapp.dto.TransitDto
import mateuszmacholl.truckapp.utils.DistanceCalculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.time.LocalDate

class TransitConverterTest {
    private val distanceCalculator = Mockito.mock(DistanceCalculator::class.java)
    private val transitConverter = TransitConverter(distanceCalculator)
    private val from = TransitDto(
            450.0,
            LocalDate.of(2018, 11, 30),
            "Wejherowo",
            "Gdynia"
    )

    @BeforeEach
    fun init(){
        Mockito.`when`(distanceCalculator.calc(from.sourceAddress, from.destinationAddress)).thenReturn(23.0)
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