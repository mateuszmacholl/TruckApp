package mateuszmacholl.truckapp.converter

import mateuszmacholl.truckapp.model.Transit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

class TransitAverageReportConverterTest {
    private val transitDailyAverageReportConverter = TransitAverageReportConverter()
    private val from = mutableListOf(
            Transit(100.0, 100.0, LocalDate.of(2018, 11,30)),
            Transit(500.0, 200.0, LocalDate.of(2018, 11,30))
    )

    @Test
    fun convert() {
        //when
        val fileEntity = transitDailyAverageReportConverter.convert(from)
        //then
        Assertions.assertEquals(150.0, fileEntity.averagePrice)
        Assertions.assertEquals(300.0, fileEntity.averageDistance)
        Assertions.assertEquals(from[0].date, fileEntity.date)
    }
}