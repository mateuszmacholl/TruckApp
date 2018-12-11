package mateuszmacholl.truckapp.utils

import mateuszmacholl.truckapp.service.LocalDateService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class LocalDateServiceTest {
    private val localDateService = LocalDateService()

    @Test
    fun getCurrentDateWithFirstDayOfMonth(){
        val date = localDateService.getCurrentDateWithFirstDayOfMonth()
        val expectedDate = LocalDate.now().withDayOfMonth(1)
        assertEquals(date, expectedDate)

    }

    @Test
    fun getCurrentDateWithOneDayAdded(){
        val date = localDateService.getCurrentDate()
        val expectedDate = LocalDate.now()
        assertEquals(date, expectedDate)
    }
}